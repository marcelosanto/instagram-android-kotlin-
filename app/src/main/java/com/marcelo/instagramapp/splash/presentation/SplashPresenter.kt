package com.marcelo.instagramapp.splash.presentation

import com.marcelo.instagramapp.splash.Splash
import com.marcelo.instagramapp.splash.data.SplashCallback
import com.marcelo.instagramapp.splash.data.SplashRepository

class SplashPresenter(private var view: Splash.View?, private val repository: SplashRepository) :
    Splash.Presenter {
    override fun authenticated() {
        repository.session(object : SplashCallback {
            override fun onSuccess() {
                view?.goToMainScreen()
            }

            override fun onFailure() {
                view?.goToLoginScreen()
            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}