package app.kotlin.unscramble.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private const val BASE_URL =
    "https://firebasestorage.googleapis.com/v0/b/chat-app-4db61.appspot.com/o/data.json?alt=media&token=8d55a584-90d9-4795-b6ab-3695fd84b9b9"

private val httpClient: HttpClient = HttpClient(Android) {
    install(plugin = ContentNegotiation) {
        json(
            contentType = ContentType(
                contentType = "application",
                contentSubtype = "json"
            )
        )
    }
}

@Serializable
data class ListOfWords(
    val listOfWords: List<Word> = emptyList()
)

@Serializable
data class Word(
    val word: String = "",
    val url: String = ""
)

suspend fun fetchData(): ListOfWords {
    val jsonRawString: String = httpClient.get(BASE_URL).bodyAsText()
    return Json.decodeFromString<ListOfWords>(jsonRawString)
}