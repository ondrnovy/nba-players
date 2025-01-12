package com.ondrnovy.nbaplayers.data.model

data class PaginatedPlayersEntity(
    val data: List<PlayerEntity>,
    val meta: PaginationMetaDataEntity,
)
