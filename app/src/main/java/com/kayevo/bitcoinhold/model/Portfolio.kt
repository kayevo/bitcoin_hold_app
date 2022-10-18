package com.kayevo.bitcoinhold.model

import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import com.kayevo.bitcoinhold.data.helper.parseSatoshiToBitcoin
import com.kayevo.bitcoinhold.data.helper.parseToCurrency

data class Portfolio(
    val id: String,
    val bitcoinAmount: String,
    val bitcoinAveragePrice: String,
){
    constructor(portfolio: PortfolioEntity): this(
        id = portfolio.id,
        bitcoinAmount = portfolio.satoshiAmount.parseSatoshiToBitcoin(),
        bitcoinAveragePrice = portfolio.bitcoinAveragePrice.parseToCurrency()
    )
}
