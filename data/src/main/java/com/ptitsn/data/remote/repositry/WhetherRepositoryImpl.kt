package com.ptitsn.data.remote.repositry

import com.ptitsn.data.mapper.RemoteToDomainMapper
import com.ptitsn.data.remote.api.WeatherApi
import com.ptitsn.data.remote.client.RestClient
import com.ptitsn.domain.model.Location
import com.ptitsn.domain.model.Weather
import com.ptitsn.domain.repository.WhetherRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WhetherRepositoryImpl @Inject constructor(val restClient: RestClient,
                                                val remoteToDomainMapper: RemoteToDomainMapper) : WhetherRepository {


    companion object {
        const val KEY = "63a475647722f14673c0f0c14555a85b"
        const val daysCount = 7
    }

    val api: WeatherApi by lazy {
        restClient.provideWeatherApi()
    }

    override fun provideCurrentLocation(loc: Location): Single<Weather> =
            api.getCurrentWeateher(KEY, mapTorequstFormta(loc))
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map { remoteToDomainMapper.map(it) }

    override fun provideWeatherForecastLocation(loc: Location): Single<List<Weather>> =
            api.getforecast(KEY, mapTorequstFormta(loc), daysCount)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map { result -> remoteToDomainMapper.map(result) }


    fun mapTorequstFormta(latLong: Location) = "${latLong.lat},${latLong.lon}"
}