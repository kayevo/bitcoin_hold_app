package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.data.result.RegisterRepoResult
import com.kayevo.bitcoinhold.data.service.UserService
import com.kayevo.bitcoinhold.model.Credential
import com.kayevo.bitcoinhold.ui.result.RegisteredEmailResult

class RegisterRepositoryImp(
    private val userService: UserService
) : RegisterRepository {

    override suspend fun register(apiKey: String, credential: Credential): RegisterRepoResult {
        return try {
            val registerResponse = userService.register(
                apiKey = apiKey, credential.email, credential.password
            )

            return when (val responseCode = registerResponse.code()) {
                HttpStatusCodeHelper.Created.code -> {
                    RegisterRepoResult.Success
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    RegisterRepoResult.ErrorServer
                }
                else -> {
                    RegisterRepoResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            RegisterRepoResult.ErrorServer
        }
    }

    override suspend fun registeredEmail(apiKey: String, email: String): RegisteredEmailResult {
        return try {
            val registeredEmailResponse = userService.registeredEmail(
                apiKey = apiKey, email = email
            )

            return when (val responseCode = registeredEmailResponse.code()) {
                HttpStatusCodeHelper.OK.code -> {
                    RegisteredEmailResult.RegisteredEmail
                }
                HttpStatusCodeHelper.NotFound.code -> {
                    RegisteredEmailResult.NotRegisteredEmail
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    RegisteredEmailResult.ErrorServer
                }
                else -> {
                    RegisteredEmailResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            RegisteredEmailResult.ErrorServer
        }
    }
}