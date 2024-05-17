package com.example.standard8.domain.model


data class ImageEntity(
    val metaResponse: MetaEntity,
    val documents: List<ImageDocumentEntity>,
)
