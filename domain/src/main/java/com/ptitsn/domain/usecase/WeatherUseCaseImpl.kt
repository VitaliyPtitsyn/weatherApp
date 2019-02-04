package com.ptitsn.domain.usecase

import com.ptitsn.domain.model.Location
import com.ptitsn.domain.model.Weather
import com.ptitsn.domain.repository.LocationRepository
import com.ptitsn.domain.repository.WhetherRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherUseCaseImpl @Inject constructor(
        val weatherRep: WhetherRepository,
        val lcoationRe: LocationRepository
) : WeatherUseCase {


    override fun provideCurrentLocation(): Single<Weather> =
            lcoationRe.provideLocation().flatMap { loc: Location ->
                weatherRep.provideCurrentLocation(loc)
            }

    override fun provideWeatherForecastLocation(): Single<List<Weather>> =
            lcoationRe.provideLocation().flatMap { loc: Location ->
                weatherRep.provideWeatherForecastLocation(loc)
            }

}

