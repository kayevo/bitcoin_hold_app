package com.kayevo.bitcoinhold.network.repository

import com.kayevo.bitcoinhold.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.network.result.LoginRepoResult
import com.kayevo.bitcoinhold.network.service.UserService
import com.kayevo.bitcoinhold.model.Credential

class LoginRepositoryImp(
    private val userService: UserService
) : LoginRepository {

    override suspend fun login(apiKey: String, credentials: Credential): LoginRepoResult {
        return try {
            val loginResponse = userService.login(
                email = credentials.email,
                password = credentials.password, apiKey = apiKey
            )
            when (val responseCode = loginResponse.code()) {
                HttpStatusCodeHelper.OK.code -> {
                    loginResponse.body()?.let { idEntity ->
                        LoginRepoResult.Success(idEntity.id)
                    } ?: run {
                        LoginRepoResult.ErrorServer
                    }
                }
                HttpStatusCodeHelper.NotFound.code -> {
                    LoginRepoResult.NotFound
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    LoginRepoResult.ErrorServer
                }
                else -> {
                    LoginRepoResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            LoginRepoResult.ErrorServer
        }
    }
}