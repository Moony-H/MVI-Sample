package com.moony.mvi_sample.mvi_viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.moony.mvi_sample.model.foundation.Intent
import com.moony.mvi_sample.model.foundation.SideEffect
import com.moony.mvi_sample.model.foundation.UIState

@Composable
fun <INTENT : Intent, UI_STATE : UIState, SIDE_EFFECT : SideEffect>
        MVIViewModel<INTENT, UI_STATE, SIDE_EFFECT>.collectAsState(): State<UI_STATE> {
    return uiState.collectAsStateWithLifecycle(initialState)
}

@Composable
fun <INTENT : Intent, UI_STATE : UIState, SIDE_EFFECT : SideEffect>
        MVIViewModel<INTENT, UI_STATE, SIDE_EFFECT>.collectSideEffect(
    onEffect: (SIDE_EFFECT) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        sideEffects.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .collect { effect -> onEffect(effect) }
    }
}
