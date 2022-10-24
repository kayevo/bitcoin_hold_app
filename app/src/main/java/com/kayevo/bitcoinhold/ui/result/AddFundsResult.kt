package com.kayevo.bitcoinhold.ui.result

sealed class AddFundsResult{
    object Success: AddFundsResult()
    object Error: AddFundsResult()
}
