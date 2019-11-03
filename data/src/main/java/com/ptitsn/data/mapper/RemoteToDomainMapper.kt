package com.ptitsn.data.mapper

import com.ptitsn.data.remote.model.RemoteCurrentWeather
import com.ptitsn.data.remote.model.RemoteForecastWeather
import com.ptitsn.domain.model.Weather
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RemoteToDomainMapper @Inject constructor() {

    private val dateTomeFormatter = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH)
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

    @Synchronized
    fun parseDateTime(date: String) = dateTomeFormatter.parse(date)

    @Synchronized
    fun parseDate(date: String) = dateFormatter.parse(date)

    fun map(it: RemoteCurrentWeather): Weather {
        return Weather(it.location.name,
                it.weather.tempC,
                parseDateTime(it.weather.lastUpdate))
    }

    fun map(forecast: RemoteForecastWeather): List<Weather> {
        val name = forecast.location.name
        return forecast.forecast.forecastDay.map { wd ->
            Weather(name, wd.day.avgtemp, parseDate(wd.date))
        }
    }
}