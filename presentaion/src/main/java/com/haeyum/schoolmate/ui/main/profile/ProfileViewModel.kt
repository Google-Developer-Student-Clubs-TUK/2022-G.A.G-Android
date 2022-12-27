package com.haeyum.schoolmate.ui.main.profile

import android.util.Log
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

    val profile = flow {
        Log.d("PangMoo", "GOGOGO")
        getProfileUseCase()?.let { profile ->
            Log.d("PangMoo", "profile: $profile")
            emit(profile)
        } ?: throw Exception("Failed to get profile")
    }
        .flowOn(Dispatchers.IO)
        .catch {
            Log.d("PangMoo", "ERROR: $it")
            _screenState.value = ProfileState.Error
        }
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = null)
}