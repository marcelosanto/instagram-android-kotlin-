package com.marcelo.instagramapp.login.data

interface LoginDataSource {
    fun login(email: String, password: String, callback: LoginCallback)
}