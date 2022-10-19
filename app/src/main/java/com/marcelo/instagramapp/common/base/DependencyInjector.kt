package com.marcelo.instagramapp.common.base

import com.marcelo.instagramapp.login.data.FakeDataSource
import com.marcelo.instagramapp.login.data.LoginRepository
import com.marcelo.instagramapp.register.data.RegisterFakeDataSource
import com.marcelo.instagramapp.register.data.RegisterRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(RegisterFakeDataSource())
    }
}