package com.kayevo.bitcoinhold.data.entity

import com.google.gson.annotations.SerializedName

data class RegisteredEntity(
    @SerializedName("registered")
    val registered: Boolean
)
