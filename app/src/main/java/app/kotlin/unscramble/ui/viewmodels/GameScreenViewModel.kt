package app.kotlin.unscramble.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.kotlin.unscramble.data.Word
import app.kotlin.unscramble.di.UnscrambleWordRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class GameUiState(
    val currentAnswer: String = "",
    val score: Int = 0,
    val timeoutPreGame: Int = 3,
    val timePlay: Int = 90,
    val currentWord: Word = Word(),
    val currentQuiz: String = "",
    val isOver: Boolean = false,
    val turn: Int = 3,
    val notification: String = "",
    val notificationShown: Boolean = false
)

class GameScreenViewModel(private val repository: UnscrambleWordRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var listOfWord = listOf<Word>()

    private val listOfQuizAnswered = mutableListOf<Word>()

    init {
        viewModelScope.launch(IO) {
            listOfWord = repository.getTheListOfWord().listOfWords
        }
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
        val unsolved: List<Word> = listOfWord - listOfQuizAnswered
        val newWord: Word = unsolved.random()
        _uiState.update { currentState ->
            currentState.copy(
                currentWord = newWord,
                currentQuiz = scrambleWord(word = newWord.word)
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

    private fun hideNotification() {
        _uiState.update { currentState ->
            currentState.copy(
                notificationShown = false
            )
        }
    }

    private fun showNotification() {
        _uiState.update { currentState ->
            currentState.copy(
                notificationShown = true
            )
        }
    }

    private fun isCorrectAnswer(): Boolean {
        return (_uiState.value.currentAnswer == _uiState.value.currentWord.word)
    }

    fun skip() {
        updateQuiz()
        _uiState.update { currentState ->
            currentState.copy(turn = 3)
        }
    }

    fun submit() {
        if (isCorrectAnswer()) {
            //Show notification
            viewModelScope.launch {
                _uiState.update { currentState ->
                    currentState.copy(notification = "Correct!")
                }
                showNotification()
                delay(timeMillis = 1000)
                hideNotification()
            }

            updateCurrentAnswer(newAnswer = "")
            getScore(score = _uiState.value.currentWord.word.length)
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
            viewModelScope.launch {
                _uiState.update { currentState ->
                    currentState.copy(notification = "Incorrect!")
                }
                showNotification()
                delay(timeMillis = 1000)
                hideNotification()
            }
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

@Suppress("UNCHECK_CAST")
class GameScreenViewModelFactory(private val repository: UnscrambleWordRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameScreenViewModel(repository) as T
    }
}