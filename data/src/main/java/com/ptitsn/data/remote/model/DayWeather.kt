package com.ptitsn.data.remote.model

import com.google.gson.annotations.SerializedName

data class DayWeather(
        @SerializedName("avgtemp") val avgtemp: Double
        )