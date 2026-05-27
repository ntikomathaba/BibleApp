package com.example.bibleapp.core.result

sealed class BaseResult<out T : Any?, out E : Any> {

    class Loading<T : Any, E : Any> : BaseResult<T, E>()

    data class Failure<out E : Any>(val error: E) : BaseResult<Nothing, E>()

    data class Success<out T : Any?>(val data: T) : BaseResult<T, Nothing>()

    fun isLoading(): Boolean = this is Loading

    fun isFailure(): Boolean = this is Failure

    fun isSuccess(): Boolean = this is Success

    fun getFailureOrNull(): Failure<E>? = this as? Failure<E>

    fun getSuccessOrNull(): Success<T>? = this as? Success<T>
}