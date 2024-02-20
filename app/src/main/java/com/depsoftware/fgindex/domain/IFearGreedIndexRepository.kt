package com.depsoftware.fgindex.domain

import com.depsoftware.fgindex.domain.model.FearGreedIndex

interface IFearGreedIndexRepository {

    suspend fun getFearGreedIndex(): List<FearGreedIndex>

    suspend fun getFearGreedIndexByType(type: FearGreedIndex.Type): FearGreedIndex?
}
