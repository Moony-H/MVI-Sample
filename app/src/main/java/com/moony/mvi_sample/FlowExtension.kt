package com.moony.mvi_sample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

fun <T> Flow<T>.throttleFirst(millis: Long): Flow<T> = channelFlow {
    var lastEmissionTime = 0L
    collect { value ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastEmissionTime >= millis) {
            lastEmissionTime = currentTime
            trySend(value)
        }
    }
}

