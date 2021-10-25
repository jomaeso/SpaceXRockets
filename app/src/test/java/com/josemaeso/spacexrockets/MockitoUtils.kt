package com.josemaeso.spacexrockets

import org.mockito.ArgumentCaptor

object MockitoUtils {
    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}