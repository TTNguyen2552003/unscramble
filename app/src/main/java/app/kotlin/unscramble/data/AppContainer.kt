package app.kotlin.unscramble.data

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

interface AppContainer {
    val listOfWordsRepository: ListOfWordsRepository
    val playersRepository: PlayersRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    private val baseUrl =
        "https://firebasestorage.googleapis.com/v0/b/unscramble-storage.appspot.com/o/data.json?alt=media&token=29a1769e-6464-4d6e-994f-e7f27b78971c"

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

    override val listOfWordsRepository: ListOfWordsRepository = NetworkListOfWordsRepository(
        httpClient = httpClient,
        baseUrl = baseUrl
    )

    override val playersRepository: PlayersRepository = LocalPlayersRepository(context = context)
}