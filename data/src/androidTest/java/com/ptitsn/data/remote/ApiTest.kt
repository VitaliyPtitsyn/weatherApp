package com.ptitsn.data.remote

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.google.gson.Gson
import com.ptitsn.data.mapper.DataToolsMapperImpl
import com.ptitsn.data.mapper.RemoteToDomainMapper
import com.ptitsn.data.remote.api.WeatherApi
import com.ptitsn.data.remote.client.RestClientImplementation
import com.ptitsn.data.remote.repositry.WhetherRepositoryImpl
import com.ptitsn.domain.model.Location
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ApiTest {

    @Rule
    @JvmField
    public var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var context: Context
    lateinit var gson: Gson
    lateinit var api: WeatherApi

    lateinit var repository: WhetherRepositoryImpl


    @Before
    fun initEnvironment() {
        context = InstrumentationRegistry.getTargetContext()
        gson = Gson()
        val restClient = RestClientImplementation(context, DataToolsMapperImpl())
        repository = WhetherRepositoryImpl(restClient, RemoteToDomainMapper())
        api = restClient.provideWeatherApi()

    }

    @Test
    fun currentWeatherTest() {
        repository.provideCurrentLocation(Location( 36.215077,49.982568))
                .test()
                .assertNoErrors()
    }

}