package com.kayevo.bitcoinhold.data.entity

import com.google.gson.annotations.SerializedName

data class PortfolioEntity(
    @SerializedName("_id")
    val id: String,

    @SerializedName("satoshiAmount")
    val satoshiAmount: Long,

    @SerializedName("bitcoinAveragePrice")
    val bitcoinAveragePrice: Double,
)
