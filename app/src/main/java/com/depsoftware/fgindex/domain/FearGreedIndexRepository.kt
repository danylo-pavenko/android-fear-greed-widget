package com.depsoftware.fgindex.domain

import com.depsoftware.fgindex.data.datasource.IFearGreedDataSource
import com.depsoftware.fgindex.domain.model.FearGreedIndex
import com.depsoftware.fgindex.domain.model.map

class FearGreedIndexRepository(private val remoteDataSource: IFearGreedDataSource): IFearGreedIndexRepository {
    override suspend fun getFearGreedIndex(): List<FearGreedIndex> {
        val indexes = remoteDataSource.fetch()!!
        return listOf(
            indexes.now.map(FearGreedIndex.Type.ACTUAL),
            indexes.yesterday.map(FearGreedIndex.Type.YESTERDAY),
            indexes.lastWeek.map(FearGreedIndex.Type.LAST_WEEK),
        )
    }

    override suspend fun getFearGreedIndexByType(type: FearGreedIndex.Type): FearGreedIndex? {
        val indexes = remoteDataSource.fetch()!!
        return when (type) {
            FearGreedIndex.Type.ACTUAL -> indexes.now.map(type)
            FearGreedIndex.Type.YESTERDAY -> indexes.yesterday.map(type)
            FearGreedIndex.Type.LAST_WEEK -> indexes.lastWeek.map(type)
        }
    }
}
