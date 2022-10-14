package com.kayevo.bitcoinhold.data.service

import com.kayevo.bitcoinhold.data.entity.UserEntity
import com.kayevo.bitcoinhold.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {

    @GET("user")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<UserEntity?>

    @POST("user")
    suspend fun register(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<Boolean>
}