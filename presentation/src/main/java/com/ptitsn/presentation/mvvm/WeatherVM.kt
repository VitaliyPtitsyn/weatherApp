package com.ptitsn.presentation.mvvm

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.ptitsn.domain.exception.LocationPermisionDenied
import com.ptitsn.domain.ui.UIErrorMapper
import com.ptitsn.domain.usecase.WeatherUseCase
import com.ptitsn.presentation.mvvm.model.*
import com.ptitsn.presentation.ui.mapper.UiModelMapper
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

open class WeatherVM @Inject constructor(
        val weatherUseCase: WeatherUseCase,
        val modelUiMapper: UiModelMapper,
        val errorMapper: UIErrorMapper
) : BaseVM() {

    /**
     * to prevent request permission and data loaded on every configChange
     */
    var ifDataWasReguested: Boolean = false


    val lvScreenState = MutableLiveData<ScreenState>()
    val lvCurrentWeather = MutableLiveData<WeatherUi>()
    val lvWeatherForecast = MutableLiveData<List<WeatherUi>>()

    val lvErrorText = Transformations.map(lvScreenState) { sc ->
        if (sc is ErrorState) {
            sc.meesage
        } else ""
    }

    fun requestLocationPermission(activity: Activity) {
        if (!ifDataWasReguested)
            RxPermissions(activity)
                    .request(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe { grunted ->
                        if (grunted)
                            updateWeather()
                        else postErrorState(LocationPermisionDenied()) { requestLocationPermission(activity) }
                    }.addTo(compositeDisposable)
    }

    fun updateWeather() {
        ifDataWasReguested = true
        getCurrentWeather(true)
    }

    fun getCurrentWeather(autoRequestForecust: Boolean = false) {
        weatherUseCase.provideCurrentLocation()
                .map { wether -> modelUiMapper.mapWeather(wether) }
                .doOnSubscribe { lvScreenState.postValue(Progress()) }
                .doAfterSuccess { if (autoRequestForecust) getWeatherForecast() }
                .delay(2, TimeUnit.SECONDS)
                .subscribe({ value ->
                    lvScreenState.postValue(Loaded())
                    lvCurrentWeather.postValue(value)
                }, { t: Throwable -> postErrorState(t) { this.getCurrentWeather(autoRequestForecust) } })
                .addTo(compositeDisposable)
    }

    fun getWeatherForecast() {
        weatherUseCase.provideWeatherForecastLocation()
                .map { it.map { wether -> modelUiMapper.mapWeather(wether) } }
                .subscribe({ value ->
                    lvWeatherForecast.postValue(value)
                }, { t: Throwable -> postErrorState(t, this::getWeatherForecast) })
                .addTo(compositeDisposable)
    }

    fun retry() {
        lvScreenState.value?.let { state ->
            if (state is ErrorState)
                state.retry.invoke()
        }
    }

    private fun postErrorState(t: Throwable, retry: () -> Unit) {
        lvScreenState.postValue(ErrorState(t, errorMapper.mapErrorToText(t), retry))
    }
}



