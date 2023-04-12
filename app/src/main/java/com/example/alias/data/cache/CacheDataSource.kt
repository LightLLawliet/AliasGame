package com.example.alias.data.cache

import com.example.alias.data.Error
import com.example.alias.data.Riddle
import io.realm.Realm

interface CacheDataSource : DataSource {
    class Base(
        private val realm: ProvideRealm,
    ) : CacheDataSource {
        override suspend fun fetch(): RiddleResult {
            realm.provideRealm().use { realm ->
                val facts = realm.where(RiddleCache::class.java).findAll()
                return RiddleResult.Success(realm.copyFromRealm(facts.random()))
            }
        }
    }
}

interface DataSource {
    suspend fun fetch(): RiddleResult
}

interface RiddleResult : Riddle {

    fun isSuccessful(): Boolean

    fun errorMessage(): String

    class Success(private val riddle: Riddle) : RiddleResult {

        override fun isSuccessful(): Boolean = true

        override fun errorMessage(): String = ""

        override suspend fun <T> map(mapper: Riddle.Mapper<T>): T {
            return riddle.map(mapper)
        }
    }

    class Failure(private val error: Error) : RiddleResult {

        override fun isSuccessful(): Boolean = false

        override fun errorMessage(): String = error.message()

        override suspend fun <T> map(mapper: Riddle.Mapper<T>): T =
            throw java.lang.IllegalStateException()
    }
}

interface ProvideRealm {
    fun provideRealm(): Realm
}