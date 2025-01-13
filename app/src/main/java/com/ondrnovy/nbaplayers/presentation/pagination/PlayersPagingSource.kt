package com.ondrnovy.nbaplayers.presentation.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ondrnovy.nbaplayers.data.PlayerRepository
import com.ondrnovy.nbaplayers.data.model.PlayerEntity

class PlayersPagingSource(
    private val playerRepository: PlayerRepository,
) : PagingSource<Int, PlayerEntity>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlayerEntity> {

        return try {
            val result = playerRepository.getPlayers(params.key, params.loadSize)
            return if (result.isSuccess) {
                result.getOrNull()?.let { paginatedPlayers ->
                    val paginationMetaData = paginatedPlayers.meta
                    val nextCursor = paginationMetaData.nextCursor

                    LoadResult.Page(
                        data = paginatedPlayers.data,
                        prevKey = null,
                        nextKey = nextCursor,
                    )
                } ?: LoadResult.Error(Exception("Empty response"))
            }
            else {
                LoadResult.Error(result.exceptionOrNull() ?: Exception("Unknown error"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlayerEntity>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}