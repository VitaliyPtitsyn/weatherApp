package com.ptitsn.data.remote.client

import android.content.Context
import com.ptitsn.data.mapper.DataToolsMapper
import com.ptitsn.data.remote.api.WeatherApi
import com.ptitsn.domain.di.AppContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


open class RestClientImplementation @Inject constructor(
        @AppContext val context: Context,
        dataTools: DataToolsMapper) : RestClient {


    companion object {
        const val BASE_URL = "http://api.weatherstack.com/"
        const val TIME_OUT = 25L
    }

    val gson = dataTools.provideGson()
    var okHttpClient = provideOkHttp()
    var retrofit: Retrofit = createRetrofit(okHttpClient)


    private fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)

        builder.addInterceptor(LogInterceptor())
        return builder.build()
    }


    private fun createRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


    override fun provideWeatherApi() = retrofit.create(WeatherApi::class.java)!!

}