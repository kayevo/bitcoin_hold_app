package com.kayevo.bitcoinhold.data.entity

import com.google.gson.annotations.SerializedName

data class AnalysisEntity(
    @SerializedName("bitcoinPriceInBrl")
    val bitcoinPriceInBrl: Double,

    @SerializedName("amountValue")
    val amountValue: Double,

    @SerializedName("profits")
    val profits: Double,
)
