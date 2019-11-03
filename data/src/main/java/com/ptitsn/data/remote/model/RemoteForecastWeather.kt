package com.ptitsn.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteForecastWeather (
        @SerializedName("location") val location: RemoteLocation,
        @SerializedName("current") val weather: RemoteWeatherData,
        @SerializedName("forecast") val forecast: RemoteForecast
        )