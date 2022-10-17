package com.kayevo.bitcoinhold.ui.result

sealed class LoginResult{
    class Success(val userId: String): LoginResult()
    object NotFound: LoginResult()
    object Error: LoginResult()
}
