package com.marcelo.instagramapp.register.data

import android.os.Handler
import android.os.Looper
import com.marcelo.instagramapp.common.model.Database

class RegisterEmailFakeDataSource : RegisterEmailDataSource {

    override fun create(email: String, callback: RegisterEmailCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull {
                email == it.email
            }

            if (userAuth == null) {
                callback.onSuccess()
            } else callback.onFailure("E-mail já cadastrado")

            callback.onComplete()

        }, 2000)
    }
}