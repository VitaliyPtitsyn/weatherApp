package com.ptitsn.presentation.mvvm

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.ptitsn.domain.exception.LocationPermisionDenied
import com.ptitsn.domain.ui.UIErrorMapper
import com.ptitsn.domain.usecase.WeatherUseCase
import com.ptitsn.presentation.mvvm.model.*
import com.ptitsn.presentation.ui.mapper.UiModelMapper
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

open class WeatherVM @Inject constructor(
        private val weatherUseCase: WeatherUseCase,
        private val modelUiMapper: UiModelMapper,
        private val errorMapper: UIErrorMapper
) : BaseVM() {

    /**
     * to prevent request permission and data loaded on every configChange
     */
    var ifDataWasRequested: Boolean = false


    val lvScreenState = MutableLiveData<ScreenState>()
    val lvCurrentWeather = MutableLiveData<WeatherUi>()

    val lvWeatherForecast = MutableLiveData<List<WeatherUi>>()
    val lvWeatherForecastVisibility: LiveData<Boolean> = Transformations.map(lvWeatherForecast)
    { list -> list.isNotEmpty() }

    val lvErrorText: LiveData<CharSequence?> = Transformations.map(lvScreenState) { sc ->
        if (sc is ErrorState) {
            sc.message
        } else ""
    }

    fun requestLocationPermission(activity: Activity) {
        if (!ifDataWasRequested)
            RxPermissions(activity)
                    .request(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe { grunted ->
                        if (grunted)
                            updateWeather()
                        else postErrorState(LocationPermisionDenied()) { requestLocationPermission(activity) }
                    }.addTo(compositeDisposable)
    }

    fun updateWeather() {
        ifDataWasRequested = true
        getCurrentWeather(true)
    }

    private fun getCurrentWeather(autoRequestForecast: Boolean = false) {
        weatherUseCase.provideCurrentLocation()
                .map { weather -> modelUiMapper.mapWeather(weather) }
                .doOnSubscribe { lvScreenState.postValue(Progress()) }
                .doAfterSuccess { if (autoRequestForecast) getWeatherForecast() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ value ->
                    lvScreenState.postValue(Loaded())
                    lvCurrentWeather.postValue(value)
                },
                        { t: Throwable -> postErrorState(t) { this.getCurrentWeather(autoRequestForecast) } })
                .addTo(compositeDisposable)
    }

    private fun getWeatherForecast() {
        weatherUseCase.provideWeatherForecastLocation()
                .map { it.map { weather -> modelUiMapper.mapWeather(weather) } }
                .observeOn(AndroidSchedulers.mainThread())
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



