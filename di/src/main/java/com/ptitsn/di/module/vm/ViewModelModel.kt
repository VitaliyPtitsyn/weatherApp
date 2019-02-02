package com.ptitsn.di.module.vm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ptitsn.presentation.mvvm.MainVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainVM::class)
    internal abstract fun splashViewModel(viewModel: MainVM): ViewModel
//

    @Binds
    internal abstract fun bindViewModelFactory(factory: WheatherViewModelFactory): ViewModelProvider.Factory
}