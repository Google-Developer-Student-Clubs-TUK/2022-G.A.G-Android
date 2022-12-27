package com.haeyum.domain.usecase.timeSchedule

import com.haeyum.domain.repository.TimeScheduleRepository
import com.haeyum.domain.usecase.device.GetIdUseCase
import com.haeyum.domain.usecase.device.GetPersonalKeyUseCase
import javax.inject.Inject

class GetTimeScheduleUseCase @Inject constructor(
    private val getIdUseCase: GetIdUseCase,
    private val getPersonalKeyUseCase: GetPersonalKeyUseCase,
    private val timeScheduleRepository: TimeScheduleRepository
) {
    suspend operator fun invoke() =
        timeScheduleRepository.getTimeSchedule(getIdUseCase()!!, getPersonalKeyUseCase()!!)
}