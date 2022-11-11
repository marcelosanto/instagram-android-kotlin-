package com.marcelo.instagramapp.common.base

import com.marcelo.instagramapp.login.data.FakeDataSource
import com.marcelo.instagramapp.login.data.LoginRepository
import com.marcelo.instagramapp.profile.data.ProfileFakeDataSource
import com.marcelo.instagramapp.profile.data.ProfileRepository
import com.marcelo.instagramapp.register.data.RegisterFakeDataSource
import com.marcelo.instagramapp.register.data.RegisterRepository
import com.marcelo.instagramapp.splash.data.FakeLocalDataSource
import com.marcelo.instagramapp.splash.data.SplashRepository

object DependencyInjector {
    fun splashRepository(): SplashRepository {
        return SplashRepository(FakeLocalDataSource())
    }

    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(RegisterFakeDataSource())
    }

    fun profileRepository(): ProfileRepository {
        return ProfileRepository(ProfileFakeDataSource())
    }


}