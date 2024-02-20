package com.depsoftware.fgindex.data.service

import com.depsoftware.fgindex.data.FearGreedStatsResponse
import retrofit2.Response
import retrofit2.http.GET

interface FearGreedService {

    @GET("/v2/fear-greed")
    suspend fun getFearGreedIndex(): Response<FearGreedStatsResponse>
}
