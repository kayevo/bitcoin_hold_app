package com.kayevo.bitcoinhold.data.service

import com.kayevo.bitcoinhold.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {

    @GET("user")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<User?>
}