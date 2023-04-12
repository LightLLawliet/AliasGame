package com.example.alias.data.cloud

import com.example.alias.data.*
import com.example.alias.data.cache.DataSource
import com.example.alias.data.cache.RiddleResult
import java.net.UnknownHostException

interface CloudDataSource : DataSource {

    class Base(
        private val factService: RiddleService,
        private val manageResources: ManageResources
    ) : CloudDataSource {

        private val noConnection by lazy {
            Error.NoConnection(manageResources)
        }
        private val serviceError by lazy {
            Error.ServiceUnavailable(manageResources)
        }

        override suspend fun fetch(): RiddleResult = try {
            val response = factService.fact().execute()
            RiddleResult.Success(response.body()!!)
        } catch (e: Exception) {
            RiddleResult.Failure(
                if (e is UnknownHostException || e is java.net.ConnectException)
                    noConnection
                else
                    serviceError
            )
        }
    }
}