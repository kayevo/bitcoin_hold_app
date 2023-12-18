package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.data.result.AdsRepoResult
import com.kayevo.bitcoinhold.data.service.AdsService

class AdsRepositoryImp(
    private val adsService: AdsService
) : AdsRepository {

    override suspend fun getAllAds(apiKey: String): AdsRepoResult {
        return try {
            val adsResponse = adsService.getAllAds(apiKey)
            when (val responseCode = adsResponse.code()) {
                HttpStatusCodeHelper.OK.code -> {
                    adsResponse.body()?.let { allAds ->
                        AdsRepoResult.Success(allAds)
                    }?: run{
                        AdsRepoResult.ErrorServer
                    }
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    AdsRepoResult.ErrorServer
                }
                else -> {
                    AdsRepoResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            AdsRepoResult.ErrorServer
        }
    }

    override suspend fun getAds(apiKey: String, email: String): AdsRepoResult {
        return try {
            val adsResponse = adsService.getAds(apiKey, email)
            when (val responseCode = adsResponse.code()) {
                HttpStatusCodeHelper.OK.code -> {
                    adsResponse.body()?.let { ads ->
                        AdsRepoResult.Success(listOf(ads))
                    }?: run{
                        AdsRepoResult.ErrorServer
                    }
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    AdsRepoResult.ErrorServer
                }
                else -> {
                    AdsRepoResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            AdsRepoResult.ErrorServer
        }
    }
}