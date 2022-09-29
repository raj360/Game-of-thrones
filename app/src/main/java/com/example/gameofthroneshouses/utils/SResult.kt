package com.example.gameofthroneshouses.utils

import com.example.gameofthroneshouses.models.base.IConvertibleTo

/**
 * Response types of result
 * Success | Loading |  Empty | Error
 */
sealed class SResult<out T : Any> {
    class Success<out T : Any>(val data: T) : SResult<T>()
    object Loading : SResult<Nothing>()
    object Empty : SResult<Nothing>()
    class Error(val code: Int, val message: String) : SResult<Nothing>()
}

/**
 * function to handle a success result
 */
inline fun <reified T : Any> successResult(data: T) = SResult.Success(data)

fun loading() = SResult.Loading
fun emptyResult() = SResult.Empty
fun errorResult(code: Int, message: String) = SResult.Error(code, message)

/**
 * function to map list results to the response object
 */
inline fun <reified O : Any, reified I : IConvertibleTo<O>> SResult<List<I>>.mapListTo(): SResult<List<O>> {
    return when (this) {
        is SResult.Success -> successResult(this.data.mapNotNull { it.convertTo() })
        is SResult.Empty -> this
        is SResult.Loading -> this
        is SResult.Error -> this
    }
}

