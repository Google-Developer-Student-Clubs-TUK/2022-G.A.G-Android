package com.haeyum.schoolmate.ui.main.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeyum.data.model.timeSchedule.TimeScheduleEntity
import com.haeyum.domain.data.notifyTime.NotifyTime
import com.haeyum.domain.data.timeSchedule.TimeSchedule
import com.haeyum.domain.data.todo.Todo
import com.haeyum.domain.usecase.profile.GetProfileUseCase
import com.haeyum.domain.usecase.timeSchedule.GetTimeScheduleUseCase
import com.haeyum.domain.usecase.todo.GetTodoUseCase
import com.haeyum.schoolmate.data.Home.TimeScheduleDto
import com.haeyum.schoolmate.data.Home.TodoDto
import com.haeyum.schoolmate.ui.main.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import utils.TimeUtil
import utils.toTimeTable
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    getTimeScheduleUseCase: GetTimeScheduleUseCase,
    getTodoUseCase: GetTodoUseCase,
    getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    private val _notifyTimeInfo: MutableState<NotifyTime> =
        mutableStateOf(NotifyTime(false, "초기값", null, null, null))
    val notifyTimeInfo: State<NotifyTime> = _notifyTimeInfo

    val timeScheduleInfo = flow {
        getTimeScheduleUseCase()?.let { timeSchedules ->
            emit(timeSchedules)
            _notifyTimeInfo.value = calNextTime(timeSchedules)
        } ?: throw Exception("Failed to get timeSchedule")
    }
        .flowOn(Dispatchers.IO)
        .catch {
            //_screenState.value = ProfileState.Error
        }
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = null)


    val todoInfo = flow {
        getTodoUseCase()?.let { todos ->
            emit(todos)
        } ?: throw Exception("Failed to get todo")
    }
        .flowOn(Dispatchers.IO)
        .catch {
            //_screenState.value = ProfileState.Error
        }
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = null)

    val profile = flow {
        getProfileUseCase()?.let { profile ->
            emit(profile)
        } ?: throw Exception("Failed to get profile")
    }
        .flowOn(Dispatchers.IO)
        .catch {
            Log.d("PangMoo", "ERROR: $it")
            //_screenState.value = ProfileState.Error
        }
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = null)


    private fun calNextTime(todoInfo: List<TimeSchedule>?): NotifyTime {

        if (todoInfo != null) {
            if (todoInfo.isEmpty()) return NotifyTime(false, "금일은 수업이 존재하지 않습니다.", null, null, null)
        }

        val curTime = TimeUtil.getCurTime()

        if (todoInfo != null) {
            for (data in todoInfo) {

                var diff = data.endTime - curTime

                if (diff < 0) {
                    continue
                }
                return if (curTime > data.startTime) {
                    NotifyTime(
                        true,
                        "수업 종료까지",
                        TimeUtil.toStringKorean(curTime - data.startTime),
                        TimeUtil.toStringAMS(data.startTime),
                        data.name
                    )
                } else {
                    NotifyTime(
                        true,
                        "다음 수업까지",
                        TimeUtil.toStringKorean(data.startTime - curTime),
                        TimeUtil.toStringAMS(data.startTime),
                        data.name
                    )
                }
            }
        }

        return NotifyTime(false, "금일의 모든 수업이 종료되었습니다.", null, null, null)
    }

}
