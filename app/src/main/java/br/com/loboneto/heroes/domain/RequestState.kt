package br.com.loboneto.heroes.domain

sealed class RequestState<out R> {

    data class Success<out T>(val data: T) : RequestState<T>()
    data class Failure(val throwable: Throwable) : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[throwable=$throwable]"
            Loading -> "Loading"
        }
    }
}
