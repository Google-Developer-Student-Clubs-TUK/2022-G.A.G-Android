
package com.haeyum.domain.usecase.timeSchedule

import com.haeyum.domain.repository.TimeScheduleRepository
import javax.inject.Inject

class GetTimeSheduelUseCase @Inject constructor(private val timeScheduleRepository: TimeScheduleRepository) {
    suspend operator fun invoke() = timeScheduleRepository.getTimeSchedule()
}