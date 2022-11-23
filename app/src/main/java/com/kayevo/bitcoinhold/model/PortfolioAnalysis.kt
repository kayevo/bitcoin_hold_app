package com.kayevo.bitcoinhold.model

import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import com.kayevo.bitcoinhold.helper.parseBitcoinPriceToSatoshiPrice
import com.kayevo.bitcoinhold.helper.parseToCurrency
import com.kayevo.bitcoinhold.helper.parseToPercentage

data class PortfolioAnalysis(
    private val bitcoinPrice: Double,
    private val portfolio: PortfolioEntity
) {
    val satoshiAmountValue: String
    val bitcoinPriceInFiat: String = bitcoinPrice.parseToCurrency()
    val profitPercentage: String

    init {
        val priceBySatoshi = bitcoinPrice.parseBitcoinPriceToSatoshiPrice()
        satoshiAmountValue = (portfolio.satoshiAmount * priceBySatoshi).parseToCurrency()

        profitPercentage = if (portfolio.bitcoinAveragePrice == 0.0) {
            0.0.parseToPercentage()
        } else {
            val priceDifference = (bitcoinPrice - portfolio.bitcoinAveragePrice)
            ((priceDifference * 100) / bitcoinPrice).parseToPercentage()
        }
    }
}