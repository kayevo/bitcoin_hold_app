package com.kayevo.bitcoinhold.network.response

import com.google.gson.annotations.SerializedName

data class PortfolioResponse(
    @SerializedName("amount")
    val amount: Long,

    @SerializedName("averagePrice")
    val averagePrice: Double,

    @SerializedName("totalPaidValue")
    val totalPaidValue: Double,
){
}
