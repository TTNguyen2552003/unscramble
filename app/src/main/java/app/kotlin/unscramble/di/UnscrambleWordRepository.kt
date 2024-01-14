package app.kotlin.unscramble.di

import app.kotlin.unscramble.data.ListOfWords
import app.kotlin.unscramble.data.fetchData

interface Repository {
    suspend fun getTheListOfWord(): ListOfWords
}

class UnscrambleWordRepository : Repository {
    override suspend fun getTheListOfWord(): ListOfWords {
        return fetchData()
    }
}