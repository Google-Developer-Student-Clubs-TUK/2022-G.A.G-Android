/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.schoolmate.ui.main.profile

import com.haeyum.domain.data.profile.Profile

sealed class ProfileState {
    object Loading : ProfileState()
    object Error : ProfileState()
    data class Success(val profile: Profile) : ProfileState()
}