package com.moony.mvi_sample.model

import com.moony.mvi_sample.model.foundation.SideEffect

sealed interface MainSideEffect : SideEffect {
    data class ShowToast(val message: String) : MainSideEffect
}
