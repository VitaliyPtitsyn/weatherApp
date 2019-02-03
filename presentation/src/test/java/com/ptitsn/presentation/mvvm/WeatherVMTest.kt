package com.ptitsn.presentation.mvvm

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.ptitsn.domain.model.Wheather
import com.ptitsn.domain.usecase.WeatherUseCase
import com.ptitsn.presentation.tools.testObserver
import duponchel.nicolas.rxbasics.RxSchedulerRule
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.sql.Date


class WeatherVMTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @InjectMocks
    lateinit var classUnderTest: WeatherVM

    @Mock
    lateinit var weatherUseCase: WeatherUseCase

    @Before
    fun setUpDate() {
        `when`(weatherUseCase.provideCurrentLocation())
                .thenReturn(Single.just(Wheather("city", 34F, Date(5465443))))
    }

    @Test
    fun `init weatherUseCase`() {
        val weatherForecast = classUnderTest.lvWeatherForecast.testObserver()
        val currentWeather = classUnderTest.lvCurrentWeather.testObserver()
        val lvProgress = classUnderTest.lvProgress.testObserver()
        Truth.assert_().that(weatherForecast.observedValues).isEmpty()
        Truth.assert_().that(lvProgress.observedValues).isEmpty()
        Truth.assert_().that(currentWeather.observedValues).isEmpty()
    }

    @Test
    fun `check Weather`() {
        val currentWeather = classUnderTest.lvCurrentWeather.testObserver()
        val lvProgress = classUnderTest.lvProgress.testObserver()

        classUnderTest.locationPermissionGrunted()

        Truth.assert_().that(lvProgress.observedValues).isEqualTo(
                listOf(true, false)
        )
        Truth.assert_().that(currentWeather.observedValues).isNotEmpty()
    }

}