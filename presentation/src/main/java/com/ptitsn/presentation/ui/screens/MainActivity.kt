package com.ptitsn.presentation.ui.screens

import com.ptitsn.presentation.R
import com.ptitsn.presentation.databinding.ActivityMainBinding
import com.ptitsn.presentation.ui.screens.base.BaseMvvmActivity

class MainActivity : BaseMvvmActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun attachViewModels(binding: ActivityMainBinding) {
        binding.name="Hello world"
    }


}
