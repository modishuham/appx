package com.m.appx.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.setVisibility(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}
