package com.moony.mvi_sample.model

import com.moony.mvi_sample.model.foundation.Intent


sealed class MainIntent : Intent {
    data object Increase : MainIntent()
    data object Decrease : MainIntent()
    data object Reset : MainIntent()
}
