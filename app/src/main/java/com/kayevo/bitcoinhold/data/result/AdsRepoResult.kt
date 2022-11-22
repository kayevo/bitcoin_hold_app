package com.kayevo.bitcoinhold.data.result

import com.kayevo.bitcoinhold.data.entity.AdsEntity
import com.kayevo.bitcoinhold.ui.result.AdsResult

sealed class AdsRepoResult{
    class Success(val allAds: List<AdsEntity>): AdsRepoResult()
    object NotFound: AdsRepoResult()
    class Error(val code: Int): AdsRepoResult()
    object ErrorServer: AdsRepoResult()
}
