package com.ptitsn.di.module

import com.ptitsn.domain.callback.IActivityListener
import com.ptitsn.domain.callback.IApplicationListener
import dagger.Module
import dagger.multibindings.Multibinds

/**
 * Created by vitaliyptitsyn on 7/3/18.
 *
 */
@Module
interface AppModule {

    @Multibinds
    fun applicationListeners(): Set<IApplicationListener>

    @Multibinds
    fun activityListeners(): Set<IActivityListener>

}