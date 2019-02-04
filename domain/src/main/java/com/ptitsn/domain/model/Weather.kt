package com.ptitsn.domain.model

import java.util.*

data class Weather(val city: CharSequence,
                   val celsius: Double,
                   val date: Date)
