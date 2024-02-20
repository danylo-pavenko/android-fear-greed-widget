package com.depsoftware.fgindex.data.datasource

import com.depsoftware.fgindex.data.FearGreedStatsResponse
import com.depsoftware.fgindex.data.service.FearGreedService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://api.coin-stats.com"

class RemoteFearGreedDataSource: IFearGreedDataSource {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: FearGreedService = retrofit.create()

    override suspend fun fetch(): FearGreedStatsResponse? {
        val response = api.getFearGreedIndex()
        return if (response.isSuccessful) {
            response.body()
        } else {
            throw Exception(response.errorBody()?.string() ?: "Failed ${response.code()}")
        }
    }
}
