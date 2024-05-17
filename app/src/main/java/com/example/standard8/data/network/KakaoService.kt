package com.example.standard8.data.network

import com.example.standard8.data.model.remote.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoService {
    @GET("image")
    suspend fun getImage(
        @Query("query") query: String,
    ):ImageResponse
}