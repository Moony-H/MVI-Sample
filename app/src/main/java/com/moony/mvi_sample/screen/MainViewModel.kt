package com.moony.mvi_sample.screen

import com.moony.mvi_sample.model.MainIntent
import com.moony.mvi_sample.model.MainSideEffect
import com.moony.mvi_sample.model.MainUIState
import com.moony.mvi_sample.mvi_viewmodel.MVIViewModel

class MainViewModel : MVIViewModel<MainIntent, MainUIState, MainSideEffect>() {
    override val initialState: MainUIState = MainUIState()
    override suspend fun handleIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.Decrease -> postUIState { copy(count = count - 1) }
            MainIntent.Increase -> postUIState { copy(count = count + 1) }
            MainIntent.Reset -> postUIState { copy(count = 0) }
            MainIntent.SideEffect -> postSideEffect(MainSideEffect.ShowToast("Side Effect"))
        }
    }
}
