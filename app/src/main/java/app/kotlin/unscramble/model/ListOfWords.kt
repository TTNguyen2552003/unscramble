package app.kotlin.unscramble.model

import kotlinx.serialization.Serializable

@Serializable
data class ListOfWords(
    val listOfWords: List<Word> = emptyList()
)

@Serializable
data class Word(
    val word: String = "",
    val url: String = ""
)