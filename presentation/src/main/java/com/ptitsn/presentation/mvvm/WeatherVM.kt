package com.ptitsn.presentation.mvvm

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import com.ptitsn.domain.exception.LocationPermisionDenied
import com.ptitsn.domain.model.Wheather
import com.ptitsn.domain.usecase.WeatherUseCase
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

open class WeatherVM @Inject constructor(
        val weatherUseCase: WeatherUseCase
) : BaseVM() {

    val lvProgress = MutableLiveData<Boolean>()
    val lvCurrentWeather = MutableLiveData<Wheather?>()
    val lvWeatherForecast = MutableLiveData<List<Wheather>>()

    fun requestLocationPermision(activity: Activity) {
        RxPermissions(activity)
                .request(android.Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe { grunted ->
                    if (grunted)
                        locationPermissionGrunted()
                    else postError(LocationPermisionDenied())
                }.addTo(compositeDisposable)
    }

     fun locationPermissionGrunted() {
        weatherUseCase
                .provideCurrentLocation()
                .doOnSubscribe { lvProgress.postValue(true) }
                .subscribe { value ->
                    lvProgress.postValue(false)
                    lvCurrentWeather.postValue(value)
                }.addTo(compositeDisposable)
    }

}
