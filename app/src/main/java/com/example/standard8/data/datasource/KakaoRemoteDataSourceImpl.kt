package com.example.standard8.data.datasource

import com.example.standard8.data.model.remote.ImageResponse
import com.example.standard8.data.network.KakaoService
import javax.inject.Inject

class KakaoRemoteDataSourceImpl @Inject constructor(private val kakaoService: KakaoService) : KakaoRemoteDataSource{
    override suspend fun getImage(query: String): ImageResponse {
        return kakaoService.getImage(query)
    }
}