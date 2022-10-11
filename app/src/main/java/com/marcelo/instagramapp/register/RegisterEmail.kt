package com.marcelo.instagramapp.register

import androidx.annotation.StringRes
import com.marcelo.instagramapp.common.base.BasePresenter
import com.marcelo.instagramapp.common.base.BaseView

interface RegisterEmail {

    interface Presenter : BasePresenter {}


    interface View : BaseView<Presenter> {
        fun displayEmailFailure(@StringRes emailError: Int?)
    }
}