package app.kotlin.unscramble.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private const val BASE_URL =
    "https://firebasestorage.googleapis.com/v0/b/chat-app-4db61.appspot.com/o/data.json?alt=media&token=429ee974-ac37-45ef-8288-efcca209da10"

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
    val jsonRawString: String = httpClient.get(BASE_URL).body()
    return Json.decodeFromString(jsonRawString)
}