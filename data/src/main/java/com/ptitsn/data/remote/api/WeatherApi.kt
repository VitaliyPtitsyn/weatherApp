package com.ptitsn.data.remote.api

import com.ptitsn.data.remote.model.RemoteCurrentWeather
import com.ptitsn.data.remote.model.RemoteForecastWeather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    /**
     * https://api.apixu.com/v1/current.json?key=7a2aa86b3a3b487aab0150123190202&q=48.8567,2.3508
     */
    @GET("current.json")
    fun getCurrentWeateher(@Query("key") key: String,
                           @Query("q") latLang: String
    ): Single<RemoteCurrentWeather>

    /**
     *https://api.apixu.com/v1/forecast.json?key=7a2aa86b3a3b487aab0150123190202&q=48.8567,2.3508&days=5
     */
    @GET("forecast.json")
    fun getforecast(
            @Query("key") key: String,
            @Query("q") latLang: String,
            @Query("days") days: Int
    ): Single<RemoteForecastWeather>

}