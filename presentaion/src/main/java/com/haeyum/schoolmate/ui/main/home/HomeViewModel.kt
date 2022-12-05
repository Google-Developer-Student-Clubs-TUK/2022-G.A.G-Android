package com.haeyum.schoolmate.ui.main.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.haeyum.schoolmate.data.Home.TimeScheduleDto
import com.haeyum.schoolmate.data.Home.TodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _timeScheduleInfo: MutableState<List<TimeScheduleDto>> = mutableStateOf(listOf())
    val timeScheduleInfo: State<List<TimeScheduleDto>> = _timeScheduleInfo

    private val _todoInfo: MutableState<List<TodoDto>> = mutableStateOf(listOf())
    val todoInfo: State<List<TodoDto>> = _todoInfo


    fun getData() {

        _timeScheduleInfo.value = listOf(
            TimeScheduleDto(location = "산융 205호", major = "수학2", time = "11:30~12:20"),
            TimeScheduleDto(location = "산융 206호", major = "수학2", time = "11:30~12:20"),
        )

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


}
