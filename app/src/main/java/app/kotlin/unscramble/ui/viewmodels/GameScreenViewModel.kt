package app.kotlin.unscramble.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class GameUiState(
    val currentAnswer: String = "",
    val score: Int = 0,
    val timeoutPreGame: Int = 3,
    val timePlay: Int = 90,
    val currentWord: String = "",
    val currentQuiz: String = "",
    val isOver: Boolean = false,
    val turn: Int = 3
)

class GameScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private val listOfWord = setOf("apple", "hind", "boat")

    private val listOfQuizAnswered = mutableSetOf<String>()

    init {
        resetGame()
    }

    private fun CharArray.covertToString(): String {
        var result = ""
        for (i: Char in this) {
            result += i
        }
        return result
    }

    private fun scrambleWord(word: String): String {
        val charArray: CharArray = word.toCharArray()
        while (charArray.covertToString() == word)
            charArray.shuffle()
        return charArray.covertToString()
    }

    private fun updateQuiz() {
        val unsolved: Set<String> = listOfWord - listOfQuizAnswered
        val newWord: String = unsolved.random()
        _uiState.update { currentState ->
            currentState.copy(
                currentWord = newWord,
                currentQuiz = scrambleWord(word = newWord)
            )
        }
    }

    private fun getScore(score: Int) {
        _uiState.update { currentState ->
            currentState.copy(score = _uiState.value.score + score)
        }
    }

    fun updateCurrentAnswer(newAnswer: String) {
        _uiState.update { currentState ->
            currentState.copy(currentAnswer = newAnswer)
        }
    }

    fun decreaseTimeoutPreGame() {
        if (_uiState.value.timeoutPreGame > 0)
            _uiState.update { currentState ->
                currentState.copy(timeoutPreGame = _uiState.value.timeoutPreGame - 1)
            }
    }

    fun decreaseTimePlay() {
        if (_uiState.value.timePlay > 0)
            _uiState.update { currentState ->
                currentState.copy(timePlay = _uiState.value.timePlay - 1)
            }
    }

    private fun isGameOver(): Boolean {
        return (
                listOfQuizAnswered.size == listOfWord.size
                        || _uiState.value.timePlay <= 0
                )
    }

    private fun makeGameOver() {
        _uiState.update { currentState ->
            currentState.copy(isOver = true)
        }
    }

    private fun isCorrectAnswer(): Boolean {
        return (_uiState.value.currentAnswer == _uiState.value.currentWord)
    }

    fun skip() {
        updateQuiz()
        _uiState.update { currentState ->
            currentState.copy(turn = 3)
        }
    }

    fun submit() {
        if (isCorrectAnswer()) {
            getScore(score = _uiState.value.currentWord.length)
            listOfQuizAnswered.add(_uiState.value.currentWord)
            if (isGameOver())
                makeGameOver()
            else {
                updateQuiz()
                _uiState.update { currentState ->
                    currentState.copy(turn = 3)
                }
            }
        } else {
            if (_uiState.value.turn == 0)
                skip()
            else
                _uiState.update { currentState ->
                    currentState.copy(turn = _uiState.value.turn - 1)
                }
        }
    }

    private fun resetGame() {
        updateQuiz()
        _uiState.update { currentState ->
            currentState.copy(
                currentAnswer = "",
                score = 0,
                timeoutPreGame = 3,
                timePlay = 90,
                isOver = false,
                turn = 3
            )
        }
    }
}