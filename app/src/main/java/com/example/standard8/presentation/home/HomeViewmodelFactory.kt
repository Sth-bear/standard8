package com.example.standard8.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.standard8.data.datasource.KakaoRemoteDataSource
import com.example.standard8.data.datasource.KakaoRemoteDataSourceImpl
import com.example.standard8.data.network.KakaoService
import com.example.standard8.data.network.RetrofitClient
import com.example.standard8.data.repository.KakaoRepositoryImpl
import com.example.standard8.domain.repository.KakaoRepository


class HomeViewmodelFactory() : ViewModelProvider.Factory{
    private val kakaoService: KakaoService = RetrofitClient.provideKakaoService()
    private val kakaoRemoteDataSource: KakaoRemoteDataSource = KakaoRemoteDataSourceImpl(kakaoService)
    private val repository: KakaoRepository = KakaoRepositoryImpl(kakaoRemoteDataSource)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}