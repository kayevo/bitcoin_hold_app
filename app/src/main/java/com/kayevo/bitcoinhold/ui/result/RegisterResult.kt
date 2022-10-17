package com.kayevo.bitcoinhold.ui.result

sealed class RegisterResult{
    object Success: RegisterResult()
    object EmailRegistered: RegisterResult()
    object Error: RegisterResult()
}
