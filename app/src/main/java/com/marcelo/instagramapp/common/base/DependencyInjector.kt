package com.marcelo.instagramapp.common.base

import com.marcelo.instagramapp.login.data.FakeDataSource
import com.marcelo.instagramapp.login.data.LoginRepository
import com.marcelo.instagramapp.register.data.RegisterEmailFakeDataSource
import com.marcelo.instagramapp.register.data.RegisterEmailRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterEmailRepository {
        return RegisterEmailRepository(RegisterEmailFakeDataSource())
    }
}