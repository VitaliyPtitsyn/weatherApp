package com.ptitsn.di.module

import com.ptitsn.data.remote.repositry.LocationRepositoryImpl
import com.ptitsn.data.remote.repositry.WhetherRepositoryImpl
import com.ptitsn.di.PerApp
import com.ptitsn.domain.repository.LocationRepository
import com.ptitsn.domain.repository.WhetherRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositroyModule {
    @Binds
    @PerApp
    fun bindWhetherRepository(uc: WhetherRepositoryImpl): WhetherRepository

    @Binds
    @PerApp
    fun bindLocationRepository(uc: LocationRepositoryImpl): LocationRepository
}