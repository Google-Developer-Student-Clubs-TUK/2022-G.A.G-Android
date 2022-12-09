/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.domain.data.todo

data class Todo(
    val subjectId: Int,
    val name: String,
    val deadline: String,
    val isDone: Boolean,
)