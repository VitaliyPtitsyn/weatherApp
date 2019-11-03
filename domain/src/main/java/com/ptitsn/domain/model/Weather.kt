package com.ptitsn.domain.model

import java.util.*

data class Weather(val region: CharSequence,
                   val celsius: Double,
                   val date: Date)
