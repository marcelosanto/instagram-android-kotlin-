package com.marcelo.instagramapp.splash

import com.marcelo.instagramapp.common.base.BasePresenter
import com.marcelo.instagramapp.common.base.BaseView

interface Splash {

    interface Presenter : BasePresenter {
        fun authenticated()
    }

    interface View : BaseView<Presenter> {
        fun goToMainScreen()
        fun goToLoginScreen()
    }
}