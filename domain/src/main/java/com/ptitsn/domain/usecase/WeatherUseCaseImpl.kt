package com.ptitsn.domain.usecase

import com.ptitsn.domain.model.Wheather
import io.reactivex.Single
import java.sql.Date
import javax.inject.Inject

class WeatherUseCaseImpl @Inject constructor() : WeatherUseCase {
    override fun provideCurrentLocation(): Single<Wheather> = Single.just(
            Wheather("city", 34F, Date(5465443)))

}

