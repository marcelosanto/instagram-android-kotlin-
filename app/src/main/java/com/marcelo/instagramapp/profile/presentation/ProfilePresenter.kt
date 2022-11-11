package com.marcelo.instagramapp.profile.presentation

import com.marcelo.instagramapp.common.base.RequestCallback
import com.marcelo.instagramapp.common.model.Database
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

        val userUUID =
            Database.sessionAuth?.uuid ?: throw RuntimeException("user not found")

        repository.fetchUserProfile(userUUID, object : RequestCallback<UserAuth> {
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
        val userUUID =
            Database.sessionAuth?.uuid ?: throw RuntimeException("user not found")

        repository.fetchUserPosts(userUUID, object : RequestCallback<List<Post>> {
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