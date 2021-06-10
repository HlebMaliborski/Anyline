package com.devloper.squad.base.presentation

import android.view.View

fun View.visibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}