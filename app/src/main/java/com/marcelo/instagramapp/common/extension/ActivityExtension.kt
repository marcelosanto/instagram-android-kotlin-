package com.marcelo.instagramapp.common.extension

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val inm: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    var view: View? = currentFocus

    if (view == null) {
        view = View(this)
    }

    inm.hideSoftInputFromWindow(view.windowToken, 0)
}