package com.ptitsn.domain.usecase

import com.ptitsn.domain.model.Weather
import io.reactivex.Single

interface WeatherUseCase {

    fun provideCurrentLocation(): Single<Weather>
    fun provideWeatherForecastLocation(): Single<List<Weather>>
}