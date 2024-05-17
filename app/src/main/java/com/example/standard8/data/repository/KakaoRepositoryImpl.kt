package com.example.standard8.data.repository

import com.example.standard8.data.datasource.KakaoRemoteDataSource
import com.example.standard8.data.util.toEntity
import com.example.standard8.domain.model.ImageEntity
import com.example.standard8.domain.repository.KakaoRepository

class KakaoRepositoryImpl(private val kakaoRemoteDataSource: KakaoRemoteDataSource): KakaoRepository {
    override suspend fun getImage(query: String): ImageEntity {
        return kakaoRemoteDataSource.getImage(query).toEntity()
    }
}

// domain은 비지니스 로직만
// data 가 실제통신을
// domain 의 repository는 인터페이스. 실제 작업을 하는건 data의 구현체(Impl)인 이곳.
// 물론 지금은 각 dataClass의 형식이 똑같지만 경우에 따라 다를 수 있음 -> 이에 until 의 mapper를 통해서 중간 다리 연결.


