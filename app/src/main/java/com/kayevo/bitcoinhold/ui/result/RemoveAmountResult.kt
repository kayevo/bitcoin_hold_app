package com.kayevo.bitcoinhold.ui.result

sealed class RemoveAmountResult{
    object Success: RemoveAmountResult()
    object InsufficientAmount: RemoveAmountResult()
    object Error: RemoveAmountResult()
}
