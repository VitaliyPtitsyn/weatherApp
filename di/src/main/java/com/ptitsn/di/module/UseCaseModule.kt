package com.ptitsn.di.module

import com.ptitsn.di.PerApp
import com.ptitsn.domain.usecase.WeatherUseCase
import com.ptitsn.domain.usecase.WeatherUseCaseImpl
import dagger.Binds
import dagger.Module

/**
 * Created by vitaliyptitsyn on 9/5/18.
 *
 */
@Module
interface UseCaseModule {


    @Binds
    @PerApp
    fun bindWheterUC(uc: WeatherUseCaseImpl): WeatherUseCase
}