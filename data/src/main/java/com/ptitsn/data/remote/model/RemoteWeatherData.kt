package com.ptitsn.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteWeatherData(
        @SerializedName("observation_time") val lastUpdate: String,
        @SerializedName("temperature") val tempC: Double
)