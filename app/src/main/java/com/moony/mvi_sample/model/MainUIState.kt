package com.moony.mvi_sample.model

import com.moony.mvi_sample.model.foundation.UIState

data class MainUIState(
    val count: Int = 0,
) : UIState
