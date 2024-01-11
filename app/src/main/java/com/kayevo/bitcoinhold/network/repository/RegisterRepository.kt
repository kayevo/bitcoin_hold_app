package com.kayevo.bitcoinhold.network.repository

import com.kayevo.bitcoinhold.ui.result.RegisteredEmailResult
import com.kayevo.bitcoinhold.network.result.RegisterRepoResult
import com.kayevo.bitcoinhold.model.Credential

interface RegisterRepository {
    suspend fun register(apiKey: String, credential: Credential): RegisterRepoResult
    suspend fun registeredEmail(apiKey: String, email: String): RegisteredEmailResult
}