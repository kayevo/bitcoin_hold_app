package com.kayevo.bitcoinhold.model

import com.kayevo.bitcoinhold.data.entity.UserEntity

data class User(
    val id: String,
    val email: String,
    val password: String
) {
    constructor(user: UserEntity): this(id = user.id, email = user.email, password = user.password)
}