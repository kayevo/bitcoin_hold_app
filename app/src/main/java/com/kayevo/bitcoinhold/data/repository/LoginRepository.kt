package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.LoginRepoResult

interface LoginRepository {
    suspend fun login(email: String, password: String): LoginRepoResult
}