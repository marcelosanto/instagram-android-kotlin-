package com.marcelo.instagramapp.login.data

import android.os.Handler
import android.os.Looper
import com.marcelo.instagramapp.common.model.Database

class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull {
                email == it.email
            }

            when {
                userAuth == null -> {
                    callback.onFailure("Usuario nao existe")
                }
                userAuth.password != password -> {
                    callback.onFailure("Senha está incorreta")
                }
                else -> {
                    Database.sessionAuth = userAuth
                    callback.onSuccess(userAuth)
                }
            }

            callback.onComplete()

        }, 2000)
    }
}