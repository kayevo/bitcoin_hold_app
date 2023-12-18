package com.kayevo.bitcoinhold.ui.result

sealed class AddAmountResult{
    object Success: AddAmountResult()
    object Error: AddAmountResult()
}
