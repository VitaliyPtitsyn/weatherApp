package com.ptitsn.presentation.ui.mapper

import android.content.res.Resources
import com.ptitsn.domain.model.Weather
import com.ptitsn.presentation.mvvm.model.WeatherUi
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


open class UiModelMapper @Inject constructor(val resoruces: Resources) {


    fun mapWeather(it: Weather): WeatherUi =
            WeatherUi(region = it.region,
                    celsius = formateTocelsi(it.celsius),
                    celsiusWithLetter = formateToCelsWitLater(it.celsius),
                    weekDay = getWeekDay(it.date))

    fun getWeekDay(date: Date): CharSequence {
        val sdf = SimpleDateFormat("EEEE")
        return sdf.format(date)
    }

    fun formateTocelsi(ce: Double): CharSequence {
        val DEGREE = "\u00b0";
        return "$ce$DEGREE"
    }

    fun formateToCelsWitLater(ce: Double): CharSequence {
        return "$ce C"
    }

}