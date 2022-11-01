package com.marcelo.instagramapp.register

import android.net.Uri
import com.marcelo.instagramapp.common.base.BasePresenter
import com.marcelo.instagramapp.common.base.BaseView

interface RegisterPhoto {

    interface Presenter : BasePresenter {
        fun updateUser(photoUri: Uri)
    }


    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)

        fun onUpdateSuccess()

        fun onUpdateFailure(message: String)
    }
}