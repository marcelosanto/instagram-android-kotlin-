package com.marcelo.instagramapp.register.data

import android.os.Handler
import android.os.Looper
import com.marcelo.instagramapp.common.model.Database
import com.marcelo.instagramapp.common.model.UserAuth
import java.util.*

class RegisterFakeDataSource : RegisterDataSource {

    override fun create(email: String, callback: RegisterCallback) {
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

    override fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull {
                email == it.email
            }

            if (userAuth != null) {
                callback.onFailure("Usuário ja existe")
            } else {
                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password)

                val created = Database.usersAuth.add(
                    newUser
                )

                if (created) {
                    Database.sessionAuth = newUser
                    callback.onSuccess()
                } else callback.onFailure("Erro interno no servidor")
            }

            callback.onComplete()

        }, 2000)
    }
}