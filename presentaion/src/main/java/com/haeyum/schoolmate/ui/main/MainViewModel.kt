package com.haeyum.schoolmate.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    /** 뒤로가기 이벤트 **/
    private val backPressEvent = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    /** 앱 종료 이벤트 **/
    val finishEvent = backPressEvent
        .scan(listOf(System.currentTimeMillis() - 2000)) { acc, _ ->
            acc.takeLast(1) + System.currentTimeMillis()
        }
        .transformLatest {
            if (it.last() - it.first() in 1..2000)
                emit(Unit)
        }
        .shareIn(scope = viewModelScope, started = SharingStarted.Lazily)

    /** 뒤로가기 스낵바 **/
    val isShowBackPressSnackbar = backPressEvent
        .transformLatest {
            emit(true)
            delay(2.seconds)
            emit(false)
        }
        .stateIn(scope = viewModelScope, started = SharingStarted.Lazily, initialValue = false)

    /** 뒤로가기 시 **/
    fun backPress() = backPressEvent.tryEmit(Unit)
}