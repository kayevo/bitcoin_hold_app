package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.entity.UserEntity
import com.kayevo.bitcoinhold.data.result.LoginRepoResult
import com.kayevo.bitcoinhold.data.service.UserService
import com.kayevo.bitcoinhold.model.User

class MockLoginRepositoryImp(
    private val userService: UserService
): LoginRepository {
    override suspend fun login(email: String, password: String): LoginRepoResult {
        val userEntity = UserEntity("id", "email@email.com", "password")
        val user = User(userEntity)
        return LoginRepoResult.Success(user)
    }
}