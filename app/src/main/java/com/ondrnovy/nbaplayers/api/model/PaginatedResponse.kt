package com.ondrnovy.nbaplayers.api.model

data class PaginatedResponse<T>(
    val data: List<T>,
    val meta: PaginationMetaData
)
