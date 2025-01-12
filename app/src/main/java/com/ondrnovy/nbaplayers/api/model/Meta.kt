package com.ondrnovy.nbaplayers.api.model

data class Meta(
    val totalPages: Int,
    val currentPage: Int,
    val nextPage: Int?,
    val perPage: Int,
    val totalCount: Int
)
