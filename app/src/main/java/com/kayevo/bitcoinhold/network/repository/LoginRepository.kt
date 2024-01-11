package com.kayevo.bitcoinhold.network.repository

import com.kayevo.bitcoinhold.network.result.LoginRepoResult
import com.kayevo.bitcoinhold.model.Credential

interface LoginRepository {
    suspend fun login(apiKey: String, credentials: Credential): LoginRepoResult
}