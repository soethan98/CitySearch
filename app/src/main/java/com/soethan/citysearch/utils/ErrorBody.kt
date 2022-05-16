package com.soethan.citysearch.utils


data class ErrorBody(
    val errMsg: String,
    val status: Int? = null
):Throwable(message = errMsg)