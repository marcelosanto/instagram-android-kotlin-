package com.marcelo.instagramapp.profile.data

import com.marcelo.instagramapp.common.model.Post
import com.marcelo.instagramapp.common.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache: ProfileCache<UserAuth>,
    private val postsCache: ProfileCache<List<Post>>,
) {
    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postsCache)
    }

    fun createFromUser(): ProfileDataSource {
        return if (profileCache.isCached()) {
            ProfileLocalDataSource(profileCache, postsCache)
        } else ProfileFakeRemoteDataSource()
    }

    fun createFromPosts(): ProfileDataSource {
        return if (postsCache.isCached()) {
            ProfileLocalDataSource(profileCache, postsCache)
        } else ProfileFakeRemoteDataSource()
    }

}