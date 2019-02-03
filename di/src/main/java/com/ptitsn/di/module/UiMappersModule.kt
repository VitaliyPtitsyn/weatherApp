package com.ptitsn.di.module

import com.ptitsn.di.PerApp
import com.ptitsn.domain.ui.UIErrorMapper
import com.ptitsn.presentation.ui.mapper.UiErrorMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface UiMappersModule {

    @Binds
    @PerApp
    fun bindUIErrorMapper(uc: UiErrorMapperImpl): UIErrorMapper
}