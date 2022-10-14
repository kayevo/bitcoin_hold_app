package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.LoginRepositoryResult
import com.kayevo.bitcoinhold.data.service.LoginService
import com.kayevo.bitcoinhold.model.User

class MockLoginRepository(
    private val loginService: LoginService
): LoginRepository {
    override suspend fun login(email: String, password: String): LoginRepositoryResult {
        return LoginRepositoryResult.Success(
            User("id", "email@email.com", "password")
        )
    }
}