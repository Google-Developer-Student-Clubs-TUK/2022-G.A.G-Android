package com.haeyum.schoolmate.ui.intro

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeyum.domain.usecase.user.PostLoginUseCase
import com.haeyum.domain.usecase.user.PostRegisterUseCase
import com.haeyum.schoolmate.utils.EncryptUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val postRegisterUseCase: PostRegisterUseCase,
    private val postLoginUseCase: PostLoginUseCase
) : ViewModel() {
    suspend fun postRegister(uuid: String) = postRegisterUseCase(uuid)
    suspend fun postLogin(uuid: String, key: String, id: String, password: String) =
        postLoginUseCase(uuid, key, id, password)

    private val SECRET_KEY = "01234567890123456789012345678901"
    private val SECRET_IV = SECRET_KEY.take(16) // 0123456789012345

    val uuid = "SECOND_PANGMOO" //UUID.randomUUID().toString()
//    val publicKey = MutableSharedFlow<String>()

    private val encryptUtil = EncryptUtil()

    companion object {
        val SECRET_KEY = "01234567890123456789012345678901"
        val SECRET_IV = SECRET_KEY.take(16)
        val uuid = "SECOND_PANGMOO"
    }

//    val publicKey = flow {
//        emit(postRegisterUseCase(uuid))
//    }.catch {
//        // Error Handling
//    }
//
//    val user = publicKey.map {
//        postLoginUseCase(uuid, SECRET_KEY, "2019156023", "!")
//    }.catch {
//        // Error Handling
//    }

    init {
        Log.d("PangMoo", "WOW")
//        viewModelScope.launch {
//            user.collectLatest {
//                if(it != null) {
//                    Log.d("PangMoo", "이름: ${it.name}")
//                    Log.d("PangMoo", "학번: ${it.studentId}")
//                    Log.d("PangMoo", "전공: ${it.major}")
//                    Log.d("PangMoo", "이메일: ${it.email}")
//                    Log.d("PangMoo", "이미지: ${it.ImageURL}")
//                } else {
//                    Log.d("PangMoo", "로그인 실패")
//                }
//            }
//        }

//        Log.d("PangMoo Fuck", "key -> ${encryptUtil.encryptRSAHex(SECRET_KEY, encryptUtil.convertPemStringToPublicKey(publicKeyString))}")
//        Log.d("PangMoo Fuck", "uuid -> $uuid")
//        Log.d("PangMoo Fuck", "id -> ${encryptUtil.encryptCBC("2019156023", SECRET_KEY, SECRET_IV)}")
//        Log.d("PangMoo Fuck", "password -> ${encryptUtil.encryptCBC("이 텍스트를 보신 당신. 이제 집에 갈 수 있지않을까?", SECRET_KEY, SECRET_IV)}")
    }
}