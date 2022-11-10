package com.marcelo.instagramapp.home.view

import com.marcelo.instagramapp.common.base.BasePresenter
import com.marcelo.instagramapp.common.base.BaseView

interface Home {

    interface Presenter : BasePresenter {
        fun fetchUserProfile()
        fun fetchUserPosts()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)

    }
}