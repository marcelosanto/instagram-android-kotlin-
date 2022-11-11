package com.marcelo.instagramapp.profile.data

import com.marcelo.instagramapp.common.base.RequestCallback
import com.marcelo.instagramapp.common.model.Post
import com.marcelo.instagramapp.common.model.UserAuth

interface ProfileDataSource {

    fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>)
    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>)

}