package com.marcelo.instagramapp.profile.data

import android.os.Handler
import android.os.Looper
import com.marcelo.instagramapp.common.base.RequestCallback
import com.marcelo.instagramapp.common.model.Database
import com.marcelo.instagramapp.common.model.Post
import com.marcelo.instagramapp.common.model.UserAuth

class ProfileFakeDataSource : ProfileDataSource {
    override fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull { userUUID == it.uuid }

            if (userAuth != null) callback.onSuccess(userAuth)
            else callback.onFailure("Usuário não encontrado")

            callback.onComplete()

        }, 2000)
    }

    override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val posts = Database.post[userUUID]

            callback.onSuccess(posts?.toList() ?: emptyList())

            callback.onComplete()

        }, 2000)
    }


}