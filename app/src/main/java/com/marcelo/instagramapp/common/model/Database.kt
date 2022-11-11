package com.marcelo.instagramapp.common.model

import java.util.*

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    val post = hashMapOf<String, Set<Post>>()

    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(
            UserAuth(
                UUID.randomUUID().toString(),
                "userA",
                "userA@gmail.com",
                "123456789"
            )
        )
        usersAuth.add(
            UserAuth(
                UUID.randomUUID().toString(),
                "userB",
                "userB@gmail.com",
                "987654321"
            )
        )

        sessionAuth = usersAuth.first()
    }
}