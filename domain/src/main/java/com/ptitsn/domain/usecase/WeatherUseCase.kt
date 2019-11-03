package com.ptitsn.domain.usecase

import com.ptitsn.domain.model.Weather
import io.reactivex.Single

interface WeatherUseCase {

    fun provideCurrentLocation(): Single<Weather>
    /** not supported for free plan*/
   // fun provideWeatherForecastLocation(): Single<List<Weather>>
}