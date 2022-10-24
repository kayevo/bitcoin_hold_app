package com.kayevo.bitcoinhold.data.repository

import android.os.Build
import android.util.Log
import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.data.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.data.result.BitcoinPriceRepoResult
import com.kayevo.bitcoinhold.data.result.LoginRepoResult
import com.kayevo.bitcoinhold.data.service.BitcoinPriceService
import com.kayevo.bitcoinhold.model.Credential
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Retrofit

class BitcoinPriceRepositoryImp(
    private val bitcoinPriceService: BitcoinPriceService
) : BitcoinPriceRepository/*, KoinComponent*/ {
    // private val retrofit: Retrofit by inject()

    override suspend fun getBitcoinPrice(): BitcoinPriceRepoResult {
        return try {
            val bitcoinPriceResponse = bitcoinPriceService.getBitcoinPrice(
                BuildConfig.CRYPTOCOMPARE_PRICE_URL
            )

            return when (val responseCode = bitcoinPriceResponse.code()){
                HttpStatusCodeHelper.OK.code ->{
                    bitcoinPriceResponse.body()?.let { bitcoinPrice ->
                        BitcoinPriceRepoResult.Success(bitcoinPrice)
                    }?: run{
                        BitcoinPriceRepoResult.ErrorServer
                    }
                }
                else ->{
                    BitcoinPriceRepoResult.Error(responseCode)
                }
            }
        }catch (e: Exception){
            Log.d("Error kayevo:", e.toString())
            BitcoinPriceRepoResult.ErrorServer
        }
    }
}