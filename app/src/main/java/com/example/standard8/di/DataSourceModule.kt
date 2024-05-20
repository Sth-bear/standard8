package com.example.standard8.di

import com.example.standard8.data.datasource.KakaoRemoteDataSource
import com.example.standard8.data.datasource.KakaoRemoteDataSourceImpl
import com.example.standard8.data.network.KakaoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideKakaoRemoteDataSource(kakaoService: KakaoService): KakaoRemoteDataSource {
        return  KakaoRemoteDataSourceImpl(kakaoService)
    }
}