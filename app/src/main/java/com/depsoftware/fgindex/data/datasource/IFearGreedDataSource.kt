package com.depsoftware.fgindex.data.datasource

import com.depsoftware.fgindex.data.FearGreedStatsResponse

interface IFearGreedDataSource {

    suspend fun fetch(): FearGreedStatsResponse?
}
