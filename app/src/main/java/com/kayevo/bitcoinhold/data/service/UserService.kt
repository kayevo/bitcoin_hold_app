package com.kayevo.bitcoinhold.data.service

import com.kayevo.bitcoinhold.data.entity.IdEntity
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("user")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<IdEntity>

    @GET("user/email")
    suspend fun registeredEmail(
        @Query("email") email: String
    ): Response<Any>

    @POST("user")
    suspend fun register(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<Any>
}