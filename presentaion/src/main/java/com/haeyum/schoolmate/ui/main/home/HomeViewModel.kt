package com.haeyum.schoolmate.ui.main.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.haeyum.data.model.timeSchedule.TimeScheduleEntity
import com.haeyum.domain.data.notifyTime.NotifyTime
import com.haeyum.domain.data.timeSchedule.TimeSchedule
import com.haeyum.schoolmate.data.Home.TimeScheduleDto
import com.haeyum.schoolmate.data.Home.TodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import utils.TimeUtil
import utils.toTimeTable
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _timeScheduleInfo: MutableState<List<TimeSchedule>> = mutableStateOf(listOf())
    val timeScheduleInfo: State<List<TimeSchedule>> = _timeScheduleInfo

    private val _todoInfo: MutableState<List<TodoDto>> = mutableStateOf(listOf())
    val todoInfo: State<List<TodoDto>> = _todoInfo

    private val _notifyTimeInfo: MutableState<NotifyTime> = mutableStateOf(NotifyTime(false, "초기값", null, null, null))

    val notifyTimeInfo: State<NotifyTime> = _notifyTimeInfo






    fun getData() {

        _timeScheduleInfo.value = listOf(
            TimeSchedule(
                name = "수학",
                room = "e동 423호",
                startTime = TimeUtil.tomillisecond("15:30"),
                endTime = TimeUtil.tomillisecond("16:30")
            ),
            TimeSchedule(
                name = "영어",
                room = "e동 423호",
                startTime = TimeUtil.tomillisecond("19:30"),
                endTime = TimeUtil.tomillisecond("23:40")
            ),
        )

        _notifyTimeInfo.value = calNextTime(_timeScheduleInfo.value)


        _todoInfo.value = listOf(
            TodoDto(
                name = "13주차 교제 과제",
                major = "영어",
                time = "11.21 오전 11:00",
                is_submit = false
            ),
            TodoDto(
                name = "13주차 교제 과제",
                major = "영어",
                time = "11.21 오전 11:00",
                is_submit = false
            ),
            TodoDto(
                name = "13주차 교제 과제",
                major = "영어",
                time = "11.21 오전 11:00",
                is_submit = true
            ),
            TodoDto(
                name = "13주차 교제 과제",
                major = "영어",
                time = "11.21 오전 11:00",
                is_submit = true
            ),
        )
    }


    private fun calNextTime(todoInfo :  List<TimeSchedule>) : NotifyTime{

        if(todoInfo.isEmpty()) return NotifyTime(false, "금일은 수업이 존재하지 않습니다.", null, null, null)

        val curTime = TimeUtil.getCurTime()
        var preName = "-1"

        for (data in todoInfo){

            var diff = data.endTime - curTime
//10시  1시수업. -> 현재 시각 11시 ->끝 시간으로 정렬? 11시에 끝나. 그럼 일단 수업대기중인데 시작시간전인지 확인
            if(diff < 0) {
                continue
            }


            return if(curTime > data.startTime){ //현재 시각 11시 수업끝나는시간 12시 , 수업시작 시간 10시  , 수업시간 11시 30분이면
                NotifyTime(true, "수업 종료까지",  TimeUtil.toStringKorean(curTime-data.startTime), TimeUtil.toStringAMS(data.startTime), data.name)
            } else{
                NotifyTime(true, "다음 수업까지", TimeUtil.toStringKorean(data.startTime-curTime), TimeUtil.toStringAMS(data.startTime), data.name)
            }
        }

        return NotifyTime(false, "금일의 모든 수업이 종료되었습니다.", null, null, null)
    }

}
