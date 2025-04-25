package com.moony.mvi_sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moony.mvi_sample.model.foundation.Intent
import com.moony.mvi_sample.model.foundation.SideEffect
import com.moony.mvi_sample.model.foundation.UIState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class MVIViewModel<INTENT : Intent, UI_STATE : UIState, SIDE_EFFECT : SideEffect> :
    ViewModel() {
    private val intents = MutableSharedFlow<INTENT>(
        replay = 0,
        extraBufferCapacity = 5,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    private val _sideEffects = Channel<SIDE_EFFECT>(Channel.BUFFERED)
    val sideEffects: Flow<SIDE_EFFECT> = _sideEffects.receiveAsFlow()

    private val _uiState: MutableStateFlow<UI_STATE> by lazy {
        MutableStateFlow(initialState)
    }
    val uiState: Flow<UI_STATE> = _uiState

    abstract val initialState: UI_STATE

    abstract fun handleIntent(intent: INTENT)


    init {
        intents.throttleFirst(1000).onEach {
            handleIntent(it)
        }.launchIn(viewModelScope)
    }

    fun postIntent(intent: INTENT) {
        intents.tryEmit(intent)
    }


    protected fun postUIState(executor: UI_STATE.() -> UI_STATE) {
        _uiState.update { executor(it) }
    }

    fun postSideEffect(sideEffect: SIDE_EFFECT) {
        _sideEffects.trySend(sideEffect)
    }

}

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
