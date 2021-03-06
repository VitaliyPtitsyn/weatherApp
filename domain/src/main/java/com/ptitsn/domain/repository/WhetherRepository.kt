package com.ptitsn.domain.repository

import com.ptitsn.domain.model.Location
import com.ptitsn.domain.model.Weather
import io.reactivex.Single

interface WhetherRepository {
    fun provideCurrentLocation(loc: Location): Single<Weather>
    fun provideWeatherForecastLocation(loc: Location): Single<List<Weather>>
}