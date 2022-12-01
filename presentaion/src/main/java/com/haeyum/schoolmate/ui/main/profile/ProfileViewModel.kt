package com.haeyum.schoolmate.ui.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeyum.domain.usecase.profile.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(getProfileUseCase: GetProfileUseCase) : ViewModel() {
    private val _screenState = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val screenState = _screenState.asStateFlow()

    // TODO 차후 설정을 제외한 프로필 정보는 로컬에 저장하여 불러오는 방식으로 변경
    val profile = flow {
        getProfileUseCase()?.let { profile ->
            emit(profile)
        } ?: throw Exception("Failed to get profile")
    }
        .flowOn(Dispatchers.IO)
        .catch {
            _screenState.value = ProfileState.Error
        }
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = null)
}