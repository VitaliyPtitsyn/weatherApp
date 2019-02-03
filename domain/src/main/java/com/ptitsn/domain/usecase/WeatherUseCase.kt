package com.ptitsn.domain.usecase

import com.ptitsn.domain.model.Wheather
import io.reactivex.Single

interface WeatherUseCase {

    fun provideCurrentLocation(): Single<Wheather>
}