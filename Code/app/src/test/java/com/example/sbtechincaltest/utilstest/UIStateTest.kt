package com.example.sbtechincaltest.utilstest

import com.example.sbtechincaltest.utils.UIState
import org.junit.Assert.assertEquals
import org.junit.Test

class UIStateTest {


    @Test
    fun successStateTest() {
        // given
        val successState = UIState.SUCCESS("success")

        // then
        assertEquals("success", successState.success)
    }

    @Test
    fun loadingStateTest() {
        // given
        val loadingState = UIState.LOADING(true)

        // then
        assertEquals(true, loadingState.isLoading)
    }

    @Test
    fun errorStateTest() {
        // given
        val error = Throwable("error")
        val errorState = UIState.ERROR(error)

        // then
        assertEquals(error, errorState.error)
    }
}