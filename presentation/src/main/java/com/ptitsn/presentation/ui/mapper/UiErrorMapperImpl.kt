package com.ptitsn.presentation.ui.mapper

import android.content.res.Resources
import com.ptitsn.domain.exception.LocationPermisionDenied
import com.ptitsn.domain.ui.UIErrorMapper
import com.ptitsn.presentation.R
import javax.inject.Inject

class UiErrorMapperImpl @Inject constructor(val resoruces: Resources) : UIErrorMapper {
    override fun mapErrorToText(t: Throwable): CharSequence =
            when (t) {
                is LocationPermisionDenied -> resoruces.getString(R.string.location_permission_denied)
                else -> resoruces.getString(R.string.something_went_wrong)
            }


}