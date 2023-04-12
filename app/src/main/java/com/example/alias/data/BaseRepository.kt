package com.example.alias.data

import com.example.alias.data.cache.CacheDataSource
import com.example.alias.data.cache.RiddleResult
import com.example.alias.data.cloud.CloudDataSource
import com.example.alias.presentation.RiddleUi

class BaseRepository(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource,
) : Repository<RiddleUi, Error> {

    private var factTemporary: Riddle? = null

    override suspend fun fetch(): RiddleResult {
        val factResult = if (getFactFromCache)
            cacheDataSource.fetch()
        else
            cloudDataSource.fetch()
        factTemporary = if (factResult.isSuccessful()) {
            factResult.map(ToDomain())
        } else null
        return factResult
    }

    private var getFactFromCache = false
}