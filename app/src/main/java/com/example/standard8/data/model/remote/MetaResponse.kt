package com.example.standard8.data.model.remote

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("is_end")
    val isEnd: Boolean
)
