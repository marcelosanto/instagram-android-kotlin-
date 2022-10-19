package com.marcelo.instagramapp.register.data

interface RegisterEmailDataSource {
    fun create(email: String, callback: RegisterEmailCallback)
}