package com.example.standard8.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.standard8.data.model.local.ImageDocument
import com.example.standard8.data.util.toData
import com.example.standard8.domain.model.ImageDocumentEntity
import com.example.standard8.domain.model.ImageEntity
import com.example.standard8.domain.repository.KakaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: KakaoRepository):ViewModel() {
    private val _images = MutableLiveData<List<ImageDocument>>()
    val images : LiveData<List<ImageDocument>> get() = _images

    private val _saved = MutableLiveData<List<ImageDocument>>()
    val saved: LiveData<List<ImageDocument>>get() = _saved

    fun searchData(param: String) {
        viewModelScope.launch {
            runCatching {
                val result = repository.getImage(param)
                _images.value = convertData(result)
            }
        }
    }

    private fun convertData(data: ImageEntity):List<ImageDocument> {
        val currentData:List<ImageDocumentEntity> = data.documents
        val convertDataList = mutableListOf<ImageDocument>()
        for(item in currentData) {
            convertDataList.add(item.toData())
        }
        return convertDataList
    }

    fun saveData(item: ImageDocument) {
        val updatedItems = (_saved.value ?: emptyList()).toMutableList()
        updatedItems.add(item)
        _saved.value = updatedItems
    }


    fun delData(item: ImageDocument) {
        val pastItems: List<ImageDocument> = _saved.value ?: emptyList()
        val updatedItems = pastItems.filter { it != item }
        _saved.value = updatedItems
    }
}