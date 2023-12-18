package com.kayevo.bitcoinhold.data.entity

import com.google.gson.annotations.SerializedName
import com.kayevo.bitcoinhold.helper.parseBitcoinToSatoshi
import com.kayevo.bitcoinhold.helper.parseCurrencyToDouble

data class PortfolioEntity(
    @SerializedName("amount")
    val amount: Long,

    @SerializedName("averagePrice")
    val averagePrice: Double,

    @SerializedName("totalPaidValue")
    val totalPaidValue: Double,
){
    /*
    constructor(portfolio: Portfolio): this(
        amount = portfolio.amount.parseBitcoinToSatoshi(),
        averagePrice = portfolio.averagePrice.parseCurrencyToDouble(),
        totalPaidValue = portfolio.totalPaidValue.parseCurrencyToDouble()
    )
    */
}
