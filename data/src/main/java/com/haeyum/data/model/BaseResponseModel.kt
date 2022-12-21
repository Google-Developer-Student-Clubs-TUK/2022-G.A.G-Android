/*
 * Created by PangMoo on 2022/11/29
 */

package com.haeyum.data.model

interface BaseResponseModel<T> {
    val code: Int
    val msg: String
    val result: T?
}