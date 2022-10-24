package com.kayevo.bitcoinhold.data.result

import com.kayevo.bitcoinhold.data.entity.BitcoinPriceEntity

sealed class BitcoinPriceRepoResult{
    class Success(val bitcoinPriceEntity: BitcoinPriceEntity): BitcoinPriceRepoResult()
    class Error(val code: Int): BitcoinPriceRepoResult()
    object ErrorServer: BitcoinPriceRepoResult()
}
