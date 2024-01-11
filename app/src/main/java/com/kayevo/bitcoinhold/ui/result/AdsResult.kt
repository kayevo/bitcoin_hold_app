package com.kayevo.bitcoinhold.ui.result

import com.kayevo.bitcoinhold.network.response.AdsResponse

sealed class AdsResult{
    class Success(val ads: AdsResponse): AdsResult()
    object NotFound: AdsResult()
    object Error: AdsResult()
}
