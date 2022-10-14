package com.kayevo.bitcoinhold.data.entity

import com.google.gson.annotations.SerializedName

data class UserEntity (
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,)
