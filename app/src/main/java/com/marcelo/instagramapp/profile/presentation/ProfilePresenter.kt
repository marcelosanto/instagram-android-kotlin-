package com.marcelo.instagramapp.profile.presentation

import com.marcelo.instagramapp.common.base.RequestCallback
import com.marcelo.instagramapp.common.model.Post
import com.marcelo.instagramapp.common.model.UserAuth
import com.marcelo.instagramapp.profile.Profile
import com.marcelo.instagramapp.profile.data.ProfileRepository

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter {

    override fun fetchUserProfile() {
        view?.showProgress(true)

        repository.fetchUserProfile(object : RequestCallback<UserAuth> {
            override fun onSuccess(data: UserAuth) {
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
            }

        })


    }

    override fun fetchUserPosts() {
        repository.fetchUserPosts(object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (data.isNotEmpty()) view?.displayFullPosts(data)
                else view?.displayEmptyPost()
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }

}