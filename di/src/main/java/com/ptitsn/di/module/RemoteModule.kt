package com.ptitsn.di.module

import com.ptitsn.data.remote.client.RestClient
import com.ptitsn.data.remote.client.RestClientImplementation
import com.ptitsn.di.PerApp
import dagger.Binds
import dagger.Module

/**
 * Created by vitaliyptitsyn on 9/5/18.
 *
 */
@Module
interface RemoteModule {


    @Binds
    @PerApp
    fun provideRestClient(client: RestClientImplementation): RestClient
}