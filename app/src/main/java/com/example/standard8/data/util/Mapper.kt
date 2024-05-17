package com.example.standard8.data.util

import com.example.standard8.data.model.local.ImageDocument
import com.example.standard8.data.model.remote.ImageDocumentResponse
import com.example.standard8.data.model.remote.ImageResponse
import com.example.standard8.data.model.remote.MetaResponse
import com.example.standard8.domain.model.ImageDocumentEntity
import com.example.standard8.domain.model.ImageEntity
import com.example.standard8.domain.model.MetaEntity

fun ImageResponse.toEntity() = ImageEntity(
    metaResponse = metaResponse.toEntity(),
    documents = documents.map {
        it.toEntity()
    }
)

fun MetaResponse.toEntity() = MetaEntity(
    totalCount = totalCount,
    pageableCount = pageableCount,
    isEnd = isEnd,
)

fun ImageDocumentResponse.toEntity() = ImageDocumentEntity (
    collection = collection,
    thumbnailUrl = thumbnailUrl,
    imageUrl = imageUrl,
    width = width,
    height = height,
    displaySiteName = displaySiteName,
    docUrl = docUrl,
    datetime = dateTime
)

fun ImageDocumentEntity.toData() = ImageDocument(
    imageUrl = imageUrl,
    displaySiteName = displaySiteName
)

