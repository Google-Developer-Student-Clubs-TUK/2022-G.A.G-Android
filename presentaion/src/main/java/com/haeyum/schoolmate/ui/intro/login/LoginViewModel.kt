/*
 * Created by PangMoo on 2022/12/27
 */

/*
 * Created by PangMoo on 2022/12/21
 */

package com.haeyum.schoolmate.ui.intro.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeyum.domain.usecase.device.GetPersonalKeyUseCase
import com.haeyum.domain.usecase.device.GetSavedUuidUseCase
import com.haeyum.domain.usecase.user.PostLoginUseCase
import com.haeyum.schoolmate.supports.Const
import com.haeyum.schoolmate.utils.EncryptUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getSavedUuidUseCase: GetSavedUuidUseCase,
    private val getPersonalKeyUseCase: GetPersonalKeyUseCase,
    private val postLoginUseCase: PostLoginUseCase,
) : ViewModel() {
    private val uuid = flow {
        emit(getSavedUuidUseCase())
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val encryptUtil = EncryptUtil()

    private val publicKey = flow {
        emit(getPersonalKeyUseCase())
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun postLogin(studentId: String, password: String, onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            if (publicKey.value != null) {
                kotlin.runCatching {
                    postLoginUseCase(
                        uuid = uuid.value!!,
                        key = getPersonalKeyUseCase()!!,
                        id = encryptUtil.encryptCBC(
                            str = studentId,
                            secretKey = Const.SECRET_KEY,
                            secretIv = Const.SECRET_IV
                        ),
                        password = encryptUtil.encryptCBC(
                            str = password,
                            secretKey = Const.SECRET_KEY,
                            secretIv = Const.SECRET_IV
                        )
                    )
                }.onSuccess {
                    if (it != null) {
                        Log.d("PangMoo", "이름: ${it.name}")
                        Log.d("PangMoo", "학번: ${it.id}")
                        Log.d("PangMoo", "전공: ${it.major}")
                        Log.d("PangMoo", "이메일: ${it.email}")
                        Log.d("PangMoo", "이미지: ${it.imageUrl}")
                    } else {
                        Log.d("PangMoo", "사용자 정보를 확인해주세요")
                    }
                    onLoginSuccess()
                }.onFailure {
                    Log.d("PangMoo", "실패: ${it.message}")
                }
            }
        }
    }
}