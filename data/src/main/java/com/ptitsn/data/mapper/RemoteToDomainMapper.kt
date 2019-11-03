package com.ptitsn.data.mapper

import android.util.Log
import com.ptitsn.data.remote.model.RemoteCurrentWeather
import com.ptitsn.data.remote.model.RemoteForecastWeather
import com.ptitsn.domain.model.Weather
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RemoteToDomainMapper @Inject constructor() {

    private val dateTomeFormatter = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH)
    private val dateFormatter = SimpleDateFormat("hh:mm a", Locale.ENGLISH)

    @Synchronized
    fun parseDateTime(date: String): Date {
        return try {
            Log.d("parse", date)
            dateTomeFormatter.parse(date)!!
        } catch (e: Exception) {
            Log.d("parse", date)
            Date()
        }
    }

    @Synchronized
    fun parseDate(date: String): Date {
        return try {
            Log.d("parse", date)
            dateFormatter.parse(date)!!
        } catch (e: Exception) {
            Log.d("parse", date)
            Date()
        }
    }

    fun map(it: RemoteCurrentWeather): Weather {
        Log.d("map current", it.toString())
        return Weather(
                it.location.name,
                it.weather.tempC,
                parseDateTime(it.weather.lastUpdate))
    }

    /** not supported for free plan*/
    fun map(forecast: RemoteForecastWeather): List<Weather> {
        val name = forecast.location.name
        return forecast.forecast.forecastDay.map { wd ->
            Weather(name, wd.day.avgtemp, parseDate(wd.date))
        }
    }
}
