package app.kotlin.unscramble.network

import app.kotlin.unscramble.model.ListOfWords
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json


suspend fun fetchData(
    httpClient: HttpClient,
    baseUrl: String
): ListOfWords {
    val jsonRawString: String = httpClient.get(baseUrl).bodyAsText()
    return Json.decodeFromString<ListOfWords>(jsonRawString)
}