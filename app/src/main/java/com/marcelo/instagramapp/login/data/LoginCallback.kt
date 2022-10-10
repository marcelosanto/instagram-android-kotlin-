package com.marcelo.instagramapp.login.data

import com.marcelo.instagramapp.common.model.UserAuth

interface LoginCallback {
    fun onSuccess(userAuth: UserAuth)
    fun onFailure(message: String)
    fun onComplete()
}