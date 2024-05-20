package com.example.standard8.di

import com.example.standard8.data.datasource.KakaoRemoteDataSource
import com.example.standard8.data.repository.KakaoRepositoryImpl
import com.example.standard8.domain.repository.KakaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideKakaoRepository(kakaoRemoteDataSource: KakaoRemoteDataSource): KakaoRepository {
        return KakaoRepositoryImpl(kakaoRemoteDataSource)
    }
}