package com.marcelo.instagramapp.login.data

import android.os.Handler
import android.os.Looper

class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            if (email == "rocha.mer@lols.com" && password == "12345678") {
                callback.onSuccess()
            } else {
                callback.onFailure("Deu ruim cabra")
            }
            callback.onComplete()
        }, 2000)
    }
}