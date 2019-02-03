package com.ptitsn.presentation.mvvm.model

enum class ScreenStateEnum { PROGRESS, LOADED, ERROR_STATE }


sealed class ScreenState(val state: ScreenStateEnum)
class Progress : ScreenState(ScreenStateEnum.PROGRESS)
class Loaded : ScreenState(ScreenStateEnum.LOADED)
data class ErrorState(val throwable: Throwable,
                      val meesage: CharSequence? = null,
                      val retry: () -> Unit) : ScreenState(ScreenStateEnum.ERROR_STATE) {
    override fun equals(other: Any?): Boolean =
            if (other is ErrorState)
                throwable::class.equals(other.throwable::class)
            else false;

    override fun hashCode(): Int {
        var result = throwable.hashCode()
        result = 31 * result + (meesage?.hashCode() ?: 0)
        result = 31 * result + retry.hashCode()
        return result
    }
}

