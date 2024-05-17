package com.example.standard8.data.model.remote

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    val documents: List<ImageDocumentResponse>,
    @SerializedName("meta")
    val metaResponse: MetaResponse
)
