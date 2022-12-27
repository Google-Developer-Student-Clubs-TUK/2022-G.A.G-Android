/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.data.model.user.subjects

@kotlinx.serialization.Serializable
data class SubjectsEntity(
    val id: String,
    val name: String,
    val isPinned: Boolean
)