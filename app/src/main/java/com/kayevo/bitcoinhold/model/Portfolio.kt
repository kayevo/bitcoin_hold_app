package com.kayevo.bitcoinhold.model

import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import com.kayevo.bitcoinhold.helper.parseSatoshiToBitcoin
import com.kayevo.bitcoinhold.helper.parseToCurrency

data class Portfolio(
    val bitcoinAmount: String,
    val bitcoinAveragePrice: String,
){
    constructor(portfolio: PortfolioEntity): this(
        bitcoinAmount = portfolio.satoshiAmount.parseSatoshiToBitcoin(),
        bitcoinAveragePrice = portfolio.bitcoinAveragePrice.parseToCurrency()
    )
}
