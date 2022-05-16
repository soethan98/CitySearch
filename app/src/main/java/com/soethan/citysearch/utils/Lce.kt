package com.soethan.citysearch.utils

sealed class Lce<out R> {
    data class Success<T>(val data: T) : Lce<T>()
    data class Error(val throwable: Throwable) : Lce<Nothing>()
    object Loading : Lce<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
            is Loading -> "Loading"
        }
    }
}


typealias ErrorResult = Lce.Error

typealias LoadingResult = Lce.Loading

typealias SuccessResult<T> = Lce.Success<T>