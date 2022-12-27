/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.schoolmate.ui.intro.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeyum.domain.usecase.device.GenerateAndSaveUuidIfNotExistsUseCase
import com.haeyum.domain.usecase.device.GetIdUseCase
import com.haeyum.domain.usecase.device.GetPersonalKeyUseCase
import com.haeyum.domain.usecase.device.SetPersonalKeyUseCase
import com.haeyum.domain.usecase.user.RegisterIfNotRegisteredUseCase
import com.haeyum.schoolmate.supports.Const
import com.haeyum.schoolmate.utils.EncryptUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.nio.channels.UnresolvedAddressException
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val generateAndSaveUuidIfNotExistsUseCase: GenerateAndSaveUuidIfNotExistsUseCase,
    private val registerIfNotRegisteredUseCase: RegisterIfNotRegisteredUseCase,
    private val setPersonalKeyUseCase: SetPersonalKeyUseCase,
    private val getPersonalKeyUseCase: GetPersonalKeyUseCase,
    private val getIdUseCase: GetIdUseCase
) : ViewModel() {
    private val _loginStatusEvent = MutableSharedFlow<SplashLoginStatusEvent>()
    val loginStatusEvent = _loginStatusEvent.asSharedFlow()

    private val _errorEvent = MutableSharedFlow<Pair<String, String>>()
    val errorEvent = _errorEvent.asSharedFlow()

    private val encryptUtil = EncryptUtil()

    init {
        viewModelScope.launch {
            // 자동 로그인 API 필요... 임시로 id로 로그인 여부 체크
            if (getIdUseCase() != null) {
                _loginStatusEvent.emit(SplashLoginStatusEvent.AlreadyLogin)
            } else {
                // 만약 UUID가 저장되어 있지 않다면 생성하고 저장한다.
                val uuid = generateAndSaveUuidIfNotExistsUseCase()

                // 만약 personal key가 저장되어 있지 않다면 서버에 요청하여 personal key를 받아온다.
                runCatching {
                    registerIfNotRegisteredUseCase(uuid)
                }.onSuccess { publicKey ->
                    if (publicKey == null) {
                        _errorEvent.emit("등록 오류" to "관리자에게 문의해주세요.")
                        return@onSuccess
                    }

                    // TODO 차후 아래 로직 UseCase 이관. EncryptUtil android.util 종속성 없애야함
                    publicKey
                        .replace("-----BEGIN RSA PUBLIC KEY-----", "")
                        .replace("-----END RSA PUBLIC KEY-----", "")
                        .replace("\n", "")
                        .let {
                            encryptUtil.convertPemStringToPublicKey(it)
                        }
                        .let { key ->
                            encryptUtil.encryptRSAHex(
                                input = Const.SECRET_KEY,
                                key = key
                            )
                        }
                        .let { encryptedKey ->
                            setPersonalKeyUseCase(encryptedKey)
                        }

                    _loginStatusEvent.emit(SplashLoginStatusEvent.RequireLogin)
                }.onFailure {
                    _errorEvent.emit(
                        when (it) {
                            is UnresolvedAddressException -> "인터넷 연결 오류" to "인터넷 연결 상태를 확인해주세요."
                            else -> "죄송합니다" to "알 수 없는 오류가 발생했습니다."
                        }
                    )
                }
            }
        }
    }

    sealed class SplashLoginStatusEvent {
        object RequireLogin : SplashLoginStatusEvent()
        object AlreadyLogin : SplashLoginStatusEvent()
    }
}