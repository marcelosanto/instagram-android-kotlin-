package com.marcelo.instagramapp.splash.data

import com.marcelo.instagramapp.common.model.Database

class FakeLocalDataSource : SplashDataSource {
    override fun session(callback: SplashCallback) {
        if (Database.sessionAuth != null) {
            callback.onSuccess()
        } else callback.onFailure()
    }
}