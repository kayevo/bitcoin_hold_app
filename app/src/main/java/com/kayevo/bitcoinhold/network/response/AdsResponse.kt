package com.kayevo.bitcoinhold.network.response

import com.google.gson.annotations.SerializedName

data class AdsResponse(
    @SerializedName("_id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("posterUrl")
    val posterUrl: String,

    @SerializedName("webLink")
    val websiteLink: String
)
