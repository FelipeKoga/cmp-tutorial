package dev.koga.cmptutorial.foundation.model


sealed interface Resource<out T> {
    data object Loading : Resource<Nothing>
    data class Success<T>(val data: T): Resource<T>
    data object Error : Resource<Nothing>
}