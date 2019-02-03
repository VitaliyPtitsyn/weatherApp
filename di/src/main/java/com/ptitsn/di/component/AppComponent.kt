package com.ptitsn.di.component

import android.content.Context
import android.content.res.Resources
import com.ptitsn.di.PerApp
import com.ptitsn.di.WhetherApp
import com.ptitsn.di.module.AppModule
import com.ptitsn.di.module.ScreensModule
import com.ptitsn.di.module.UiMappersModule
import com.ptitsn.di.module.UseCaseModule
import com.ptitsn.di.module.vm.ViewModelModule
import com.ptitsn.domain.callback.IApplicationListener
import com.ptitsn.domain.di.AppContext
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@PerApp
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ScreensModule::class,
    ViewModelModule::class,
    UiMappersModule::class,
    UseCaseModule::class
])
interface AppComponent : AndroidInjector<WhetherApp> {

    fun applicationListeners(): @JvmSuppressWildcards Set<IApplicationListener>

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(@AppContext context: Context): Builder

        @BindsInstance
        fun applicationResources(res: Resources): Builder

        fun build(): AppComponent
    }
}