package com.kayevo.bitcoinhold.ui.result

sealed class LoginViewResult{
    object Success: LoginViewResult()
    object NotFound: LoginViewResult()
    object ErrorLogging: LoginViewResult()
}
