package com.ptitsn.data.remote.repositry

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.ptitsn.domain.di.AppContext
import com.ptitsn.domain.exception.NoAvaibelLocation
import com.ptitsn.domain.repository.LocationRepository
import io.reactivex.Single
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
        @AppContext val context: Context
) : LocationRepository {

    @SuppressLint("MissingPermission")
    override fun provideLocation(): Single<com.ptitsn.domain.model.Location> = Single.create { emiter ->
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null)
                emiter.onSuccess(com.ptitsn.domain.model.Location(lon = location.longitude, lat = location.latitude))
            emiter.onError(NoAvaibelLocation())
        }.addOnFailureListener {
            emiter.onError(NoAvaibelLocation())
        }
    }

}
