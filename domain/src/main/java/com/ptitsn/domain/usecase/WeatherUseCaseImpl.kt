package com.ptitsn.domain.usecase

import com.ptitsn.domain.model.Weather
import io.reactivex.Single
import java.sql.Date
import javax.inject.Inject

class WeatherUseCaseImpl @Inject constructor() : WeatherUseCase {


    override fun provideCurrentLocation(): Single<Weather> =
            Single.just(Weather("city", 34f, Date(5465443)))

    override fun provideWeatherForecastLocation(): Single<List<Weather>> = Single.just(
            listOf(
                    Weather("city", 34F, Date(5465443)),
                    Weather("city", 34F, Date(5465443)),
                    Weather("city", 34F, Date(5465443)))
    )

}

