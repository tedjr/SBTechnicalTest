package com.example.sbtechincaltest.utils

sealed class UIState {

    /*sealed class with subclasses allows for the creation of a finite number of possible states that can be handled easily with a when expression.
    This promotes type safety and helps prevent errors that may occur when using less type-safe approaches.*/

    class SUCCESS(val success: Any) : UIState()
    class LOADING(val isLoading: Boolean = true) : UIState()
    class ERROR(val error: Throwable) : UIState()
}