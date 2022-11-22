package com.kayevo.bitcoinhold.ui.result

import com.kayevo.bitcoinhold.data.entity.AdsEntity

sealed class AdsResult{
    class Success(val ads: AdsEntity): AdsResult()
    object NotFound: AdsResult()
    object Error: AdsResult()
}
