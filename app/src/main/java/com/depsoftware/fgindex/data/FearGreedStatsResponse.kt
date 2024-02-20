package com.depsoftware.fgindex.data

data class FearGreedStatsResponse(
    val name: String,
    val now: FearGreedIndexResponse,
    val yesterday: FearGreedIndexResponse,
    val lastWeek: FearGreedIndexResponse,
)
