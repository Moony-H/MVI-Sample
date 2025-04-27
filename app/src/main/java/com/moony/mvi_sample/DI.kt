package com.moony.mvi_sample

import com.moony.mvi_sample.delegate.DecreaseIntentHandler
import com.moony.mvi_sample.delegate.DecreaseIntentHandlerImpl
import com.moony.mvi_sample.delegate.IncreaseIntentHandler
import com.moony.mvi_sample.delegate.IncreaseIntentHandlerImpl
import com.moony.mvi_sample.delegate.ResetIntentHandler
import com.moony.mvi_sample.delegate.ResetIntentHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DI {
    @Binds
    @Singleton
    abstract fun bindIncreaseIntentHandler(
        increaseIntentHandlerImpl: IncreaseIntentHandlerImpl
    ): IncreaseIntentHandler

    @Binds
    @Singleton
    abstract fun bindDecreaseIntentHandler(
        decreaseIntentHandlerImpl: DecreaseIntentHandlerImpl
    ): DecreaseIntentHandler

    @Binds
    @Singleton
    abstract fun bindResetIntentHandler(
        resetIntentHandlerImpl: ResetIntentHandlerImpl
    ): ResetIntentHandler
}
