package com.example.standard8.domain.repository

import com.example.standard8.domain.model.ImageEntity

interface KakaoRepository {
    suspend fun getImage(
        query: String,
    ): ImageEntity
}