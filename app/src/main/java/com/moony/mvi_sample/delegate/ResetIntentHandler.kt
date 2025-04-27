package com.moony.mvi_sample.delegate

import com.moony.mvi_sample.model.MainIntent
import com.moony.mvi_sample.model.MainUIState
import javax.inject.Inject

interface ResetIntentHandler {
    suspend fun handleResetIntent(
        intent: MainIntent.Reset,
        executor: (MainUIState.() -> MainUIState) -> Unit
    )
}

class ResetIntentHandlerImpl @Inject constructor(): ResetIntentHandler {
    override suspend fun handleResetIntent(
        intent: MainIntent.Reset,
        executor: (MainUIState.() -> MainUIState) -> Unit
    ) {
        executor { copy(count = 0) }
    }
}
