package com.ptitsn.di.module

import com.ptitsn.di.PerActivity
import com.ptitsn.presentation.ui.screens.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ScreensModule {

    @PerActivity
    @ContributesAndroidInjector()
    fun mainActivity(): MainActivity


}