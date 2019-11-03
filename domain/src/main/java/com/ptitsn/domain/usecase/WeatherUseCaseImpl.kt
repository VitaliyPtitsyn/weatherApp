package com.ptitsn.domain.usecase

import com.ptitsn.domain.model.Location
import com.ptitsn.domain.model.Weather
import com.ptitsn.domain.repository.LocationRepository
import com.ptitsn.domain.repository.WhetherRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherUseCaseImpl @Inject constructor(
        private val weatherRep: WhetherRepository,
        private val locationRe: LocationRepository
) : WeatherUseCase {


    override fun provideCurrentLocation(): Single<Weather> =
            locationRe
                    .provideLocation()
                    .flatMap { loc: Location ->
                        weatherRep
                                .provideCurrentLocation(loc)
                    }
    /** not supported for free plan*/
//    override fun provideWeatherForecastLocation(): Single<List<Weather>> =
//            locationRe.provideLocation().flatMap { loc: Location ->
//                weatherRep.provideWeatherForecastLocation(loc)
//            }

}

