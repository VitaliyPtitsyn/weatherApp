package com.ptitsn.domain.ui

interface UIErrorMapper {
    fun mapErrorToText(t: Throwable): CharSequence
}