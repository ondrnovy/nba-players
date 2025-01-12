package com.ondrnovy.nbaplayers.api.model

data class PaginationMetaData(
    val totalPages: Int,
    val currentPage: Int,
    val nextPage: Int?,
    val perPage: Int,
    val totalCount: Int
)
