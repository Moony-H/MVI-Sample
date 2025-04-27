package com.moony.mvi_sample.delegate

import com.moony.mvi_sample.model.MainIntent
import com.moony.mvi_sample.model.MainUIState
import javax.inject.Inject

interface IncreaseIntentHandler {
    suspend fun handleIncreaseIntent(
        intent: MainIntent.Increase,
        executor: (MainUIState.() -> MainUIState) -> Unit
    )
}

class IncreaseIntentHandlerImpl @Inject constructor() : IncreaseIntentHandler {
    override suspend fun handleIncreaseIntent(
        intent: MainIntent.Increase,
        executor: (MainUIState.() -> MainUIState) -> Unit
    ) {
        executor { copy(count = count + 1) }
    }
}
