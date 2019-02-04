package com.ptitsn.domain.repository

import com.ptitsn.domain.model.Location
import io.reactivex.Single

interface LocationRepository {
    fun provideLocation(): Single<Location>

}