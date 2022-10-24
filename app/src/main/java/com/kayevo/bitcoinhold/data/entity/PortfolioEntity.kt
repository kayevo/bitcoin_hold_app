package com.kayevo.bitcoinhold.data.entity

import com.google.gson.annotations.SerializedName
import com.kayevo.bitcoinhold.data.helper.parseBitcoinToSatoshi
import com.kayevo.bitcoinhold.data.helper.parseCurrencyToDouble
import com.kayevo.bitcoinhold.model.Portfolio

data class PortfolioEntity(
    @SerializedName("satoshiAmount")
    val satoshiAmount: Long,

    @SerializedName("bitcoinAveragePrice")
    val bitcoinAveragePrice: Double,
){
    constructor(portfolio: Portfolio): this(
        satoshiAmount = portfolio.bitcoinAmount.parseBitcoinToSatoshi(),
        bitcoinAveragePrice = portfolio.bitcoinAveragePrice.parseCurrencyToDouble()
    )
}
