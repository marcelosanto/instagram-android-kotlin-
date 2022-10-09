package com.marcelo.instagramapp.common.base

import com.marcelo.instagramapp.login.data.FakeDataSource
import com.marcelo.instagramapp.login.data.LoginRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }
}