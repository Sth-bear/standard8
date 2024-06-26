package com.example.standard8.domain.model

import java.util.Date

data class ImageDocumentEntity(
    val collection: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val displaySiteName: String,
    val docUrl: String,
    val datetime: Date,
)
