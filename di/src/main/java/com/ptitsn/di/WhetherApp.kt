package com.ptitsn.di

import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins

/**
 * Created by vitaliyptitsyn on 8/23/18.
 *
 */
class WhetherApp : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? =
            AppInjector.init(this)

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { throwable ->
            Log.e("WhetherApp", "handled rx exception ${throwable::class.java.simpleName} " +
                    "\n\tmessage: \t${throwable.message}", throwable)
        }

    }

}