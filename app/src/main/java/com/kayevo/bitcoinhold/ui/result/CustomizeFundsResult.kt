package com.kayevo.bitcoinhold.ui.result

sealed class CustomizeFundsResult{
    object Success: CustomizeFundsResult()
    object Error: CustomizeFundsResult()
}
