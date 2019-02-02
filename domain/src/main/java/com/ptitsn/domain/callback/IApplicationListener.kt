package com.ptitsn.domain.callback

import android.app.Application

interface IApplicationListener {
    fun onCreate(application: Application)
}
