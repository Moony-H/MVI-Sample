package com.moony.mvi_sample.delegate

import com.moony.mvi_sample.model.MainIntent
import com.moony.mvi_sample.model.MainUIState
import javax.inject.Inject

interface DecreaseIntentHandler {
    suspend fun handleDecreaseIntent(
        intent: MainIntent.Decrease,
        executor: (MainUIState.() -> MainUIState) -> Unit
    )
}

class DecreaseIntentHandlerImpl @Inject constructor() : DecreaseIntentHandler {
    override suspend fun handleDecreaseIntent(
        intent: MainIntent.Decrease,
        executor: (MainUIState.() -> MainUIState) -> Unit
    ) {
        executor { copy(count = count - 1) }
    }
}
