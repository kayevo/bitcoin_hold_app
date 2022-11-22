package com.kayevo.bitcoinhold.data.entity

import com.google.gson.annotations.SerializedName

data class AdsEntity(
    @SerializedName("_id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("posterUrl")
    val posterUrl: String,

    @SerializedName("webLink")
    val websiteLink: String
)
