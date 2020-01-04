package com.bragonya.daggerdemo.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun Context.inflate(
    @LayoutRes id: Int,
    container: ViewGroup?,
    attachToRoot: Boolean = container != null
): View {
    return LayoutInflater.from(this).inflate(id, container, attachToRoot)
}