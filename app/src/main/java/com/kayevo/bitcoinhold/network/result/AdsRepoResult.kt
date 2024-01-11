package com.kayevo.bitcoinhold.network.result

import com.kayevo.bitcoinhold.network.response.AdsResponse

sealed class AdsRepoResult{
    class Success(val allAds: List<AdsResponse>): AdsRepoResult()
    object NotFound: AdsRepoResult()
    class Error(val code: Int): AdsRepoResult()
    object ErrorServer: AdsRepoResult()
}
