package com.ptitsn.data.remote.client

import com.ptitsn.data.remote.api.WeatherApi

/**
 * Created by vitaliyptitsyn on 9/5/18.
 *
 */
interface RestClient {
    fun provideWeatherApi(): WeatherApi
}