package com.moony.mvi_sample.screen


import com.moony.mvi_sample.delegate.DecreaseIntentHandler
import com.moony.mvi_sample.delegate.IncreaseIntentHandler
import com.moony.mvi_sample.delegate.ResetIntentHandler
import com.moony.mvi_sample.model.MainIntent
import com.moony.mvi_sample.model.MainSideEffect
import com.moony.mvi_sample.model.MainUIState
import com.moony.mvi_sample.mvi_viewmodel.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val increaseIntentHandler: IncreaseIntentHandler,
    private val decreaseIntentHandler: DecreaseIntentHandler,
    private val resetIntentHandler: ResetIntentHandler
) : MVIViewModel<MainIntent, MainUIState, MainSideEffect>(),
    IncreaseIntentHandler by increaseIntentHandler,
    DecreaseIntentHandler by decreaseIntentHandler,
    ResetIntentHandler by resetIntentHandler {

    override val initialState: MainUIState = MainUIState().apply {
    }

    override suspend fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.Increase -> handleIncreaseIntent(intent, this::postUIState)
            is MainIntent.Decrease -> handleDecreaseIntent(intent, this::postUIState)
            is MainIntent.Reset -> handleResetIntent(intent, this::postUIState)
        }
    }
}

