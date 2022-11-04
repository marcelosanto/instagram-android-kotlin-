package com.marcelo.instagramapp.splash.data

interface SplashDataSource {
    fun session(callback: SplashCallback)
}