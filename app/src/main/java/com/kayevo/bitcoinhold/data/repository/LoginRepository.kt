package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.LoginRepositoryResult

interface LoginRepository {
    suspend fun login(email: String, password: String): LoginRepositoryResult
}