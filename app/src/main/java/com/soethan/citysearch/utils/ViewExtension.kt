package com.soethan.citysearch.utils

import android.content.Context
import android.view.View
import android.widget.Toast


fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.isVisible(): Boolean {
    if (visibility == View.VISIBLE) {
        return true
    }
    return false
}

fun View.invisible(): View {
    if (visibility != View.GONE) {
        visibility = View.INVISIBLE
    }
    return this
}

fun Context.toast(text: String? = "Something went wrong", duration: Int = Toast.LENGTH_LONG) {
    return this.let {
        Toast.makeText(it, text, duration).show()

    }
}