package com.example.standard8.data.datasource

import com.example.standard8.data.model.remote.ImageResponse

interface KakaoRemoteDataSource {
    suspend fun getImage(
        query: String
    ): ImageResponse
}