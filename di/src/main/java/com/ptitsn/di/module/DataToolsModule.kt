package com.ptitsn.di.module

import com.ptitsn.data.mapper.DataToolsMapper
import com.ptitsn.data.mapper.DataToolsMapperImpl
import com.ptitsn.di.PerApp
import dagger.Binds
import dagger.Module

@Module
interface DataToolsModule {

    @Binds
    @PerApp
    fun bindDataToolsModulee(uc: DataToolsMapperImpl): DataToolsMapper
}