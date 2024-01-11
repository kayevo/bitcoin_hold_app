package com.kayevo.bitcoinhold.network.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: String
)
