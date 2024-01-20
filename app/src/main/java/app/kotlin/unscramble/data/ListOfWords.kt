package app.kotlin.unscramble.data

import app.kotlin.unscramble.model.ListOfWords
import app.kotlin.unscramble.network.fetchData
import io.ktor.client.HttpClient

interface ListOfWordsRepository {
    suspend fun getTheListOfWords(): ListOfWords
}

class NetworkListOfWordsRepository(
    val httpClient: HttpClient,
    val baseUrl:String
) : ListOfWordsRepository {
    override suspend fun getTheListOfWords(): ListOfWords {
        return fetchData(
            httpClient = httpClient,
            baseUrl = baseUrl
        )
    }
}