package com.kayevo.bitcoinhold.ui.result

sealed class CustomizeAmountResult{
    object Success: CustomizeAmountResult()
    object Error: CustomizeAmountResult()
}
