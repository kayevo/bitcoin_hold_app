package com.kayevo.bitcoinhold.ui.result

sealed class RemoveFundsResult{
    object Success: RemoveFundsResult()
    object InsufficientFunds: RemoveFundsResult()
    object Error: RemoveFundsResult()
}
