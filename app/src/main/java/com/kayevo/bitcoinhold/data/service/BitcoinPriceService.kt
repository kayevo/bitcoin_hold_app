package com.kayevo.bitcoinhold.data.service

import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.data.entity.BitcoinPriceEntity
import com.kayevo.bitcoinhold.data.entity.IdEntity
import retrofit2.Response
import retrofit2.http.*

interface BitcoinPriceService {

    @GET// ("data/price")
    suspend fun getBitcoinPrice(
        @Url url: String,
        @Query("fsym") cryptoSymbol: String = "BTC",
        @Query("tsyms") fiatSymbol: String = "BRL",
        @Query("api_key") apiKey: String = BuildConfig.CRYPTOCOMPARE_API_KEY,
    ): Response<BitcoinPriceEntity>
}