package com.kayevo.bitcoinhold.data.entity

import com.google.gson.annotations.SerializedName

data class BitcoinPriceEntity(
    @SerializedName("BRL")
    val priceInBRL: Double
)
