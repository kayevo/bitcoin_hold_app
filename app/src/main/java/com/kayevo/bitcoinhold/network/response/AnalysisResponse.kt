package com.kayevo.bitcoinhold.network.response

import com.google.gson.annotations.SerializedName

data class AnalysisResponse(
    @SerializedName("bitcoinPriceInBrl")
    val bitcoinPriceInBrl: Double,

    @SerializedName("amountValue")
    val amountValue: Double,

    @SerializedName("profits")
    val profits: Double,
)
