package com.ptitsn.presentation.ui.screens

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import com.ptitsn.presentation.R
import com.ptitsn.presentation.databinding.ActivityMainBinding
import com.ptitsn.presentation.mvvm.WeatherVM
import com.ptitsn.presentation.ui.screens.base.BaseMvvmActivity

class MainActivity : BaseMvvmActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var weatherViewModel: WeatherVM

    override fun attachViewModels(binding: ActivityMainBinding) {
        weatherViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(WeatherVM::class.java)

        binding.vm = weatherViewModel
        weatherViewModel.requestLocationPermission(this)

        weatherViewModel.lvScreenState.observe(this, Observer {
            Log.d("!!!!", "$it")
        })
    }


}
