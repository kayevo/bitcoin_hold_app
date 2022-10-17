package com.kayevo.bitcoinhold.data.repository

import android.util.Log
import android.util.Log.e
import com.kayevo.bitcoinhold.data.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.data.result.LoginRepoResult
import com.kayevo.bitcoinhold.data.service.UserService
import com.kayevo.bitcoinhold.model.Credential

class LoginRepositoryImp(
    private val userService: UserService
) : LoginRepository {

    override suspend fun login(credentials: Credential): LoginRepoResult {
        return try {
            val loginResponse = userService.login(credentials.email, credentials.password)
            when (val responseCode = loginResponse.code()){
                HttpStatusCodeHelper.OK.code ->{
                    loginResponse.body()?.let { idEntity ->
                        LoginRepoResult.Success(idEntity.id)
                    }?: run{
                        LoginRepoResult.ErrorServer
                    }
                }
                HttpStatusCodeHelper.NotFound.code ->{
                    LoginRepoResult.NotFound
                }
                HttpStatusCodeHelper.InternalServerError.code ->{
                    LoginRepoResult.ErrorServer
                }
                else ->{
                    LoginRepoResult.Error(responseCode)
                }
            }
        }catch (e: Exception){
            LoginRepoResult.ErrorServer
        }
    }
}