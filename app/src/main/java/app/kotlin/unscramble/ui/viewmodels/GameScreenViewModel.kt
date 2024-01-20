package app.kotlin.unscramble.ui.viewmodels

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.kotlin.unscramble.UnscrambleApplication
import app.kotlin.unscramble.data.ListOfWordsRepository
import app.kotlin.unscramble.data.Player
import app.kotlin.unscramble.data.PlayersRepository
import app.kotlin.unscramble.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

enum class InternetState {
    LOADING,
    SUCCESSFUL,
    FAILED
}

data class GameUiState(
    val internetState: InternetState = InternetState.LOADING,
    val currentAnswer: String = "",
    val userName: String = "Unknown",
    val score: Int = 0,
    val winningStreak: Int = 0,
    val timeoutPreGame: Int = 3,
    val timePlay: Int = 90,
    val currentWord: Word = Word(),
    val currentQuiz: String = "",
    val isOver: Boolean = false,
    val turn: Int = 3,
    val notification: String = "",
    val notificationShown: Boolean = false
)

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class GameScreenViewModel(
    private val listOfWordsRepository: ListOfWordsRepository,
    private val playersRepository: PlayersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var listOfWords: List<Word> = mutableListOf()
    private var listOfWordsAnswered = mutableListOf<Word>()

    init {
        initGame()
    }

    fun updateUserName(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                userName = newName
            )
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun initGame() {
        viewModelScope.launch {
            try {
                _uiState.update { currentState ->
                    currentState.copy(internetState = InternetState.LOADING)
                }

                withContext(Dispatchers.IO) {
                    listOfWords = listOfWordsRepository.getTheListOfWords().listOfWords
                }

                updateQuiz()

                _uiState.update { currentState ->
                    currentState.copy(
                        internetState = InternetState.SUCCESSFUL,
                    )
                }
            } catch (e: IOException) {
                _uiState.update { currentState ->
                    currentState.copy(internetState = InternetState.FAILED)
                }
            } catch (e: HttpException) {
                _uiState.update { currentState ->
                    currentState.copy(internetState = InternetState.FAILED)
                }
            }
        }
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
        val unsolved: List<Word> = listOfWords - listOfWordsAnswered
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
        else {
            makeGameOver()
        }
    }

    private fun makeGameOver() {
        _uiState.update { currentState ->
            currentState.copy(isOver = true)
        }

        viewModelScope.launch(Dispatchers.IO) {
            playersRepository.record(
                player = Player(
                    playerName = _uiState.value.userName,
                    score = _uiState.value.score
                )
            )
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
            currentState.copy(
                turn = 3,
                winningStreak = 0
            )
        }
    }

    fun submit() {
        if (isCorrectAnswer()) {
            //Increase winning streak
            _uiState.update { currentState ->
                currentState.copy(
                    winningStreak = _uiState.value.winningStreak + 1
                )
            }

            //Show notification
            viewModelScope.launch {
                //When player get bonus: bonus = winning streak / 3
                if (_uiState.value.winningStreak / 3 > 0) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            notification = "Correct! Bonus +${_uiState.value.winningStreak / 3}",
                        )
                    }
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(
                            notification = "Correct!"
                        )
                    }
                }
                showNotification()
                delay(timeMillis = 1000)
                hideNotification()
            }

            updateCurrentAnswer(newAnswer = "")

            getScore(score = _uiState.value.currentWord.word.length + _uiState.value.winningStreak / 3)

            listOfWordsAnswered.add(_uiState.value.currentWord)
            if (listOfWords.size == listOfWordsAnswered.size) {
                makeGameOver()
            } else {
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

    fun resetGame() {
        listOfWordsAnswered = mutableListOf()
        updateQuiz()
        _uiState.update { currentState ->
            currentState.copy(
                currentAnswer = "",
                score = 0,
                winningStreak = 0,
                timeoutPreGame = 3,
                timePlay = 90,
                isOver = false,
                turn = 3,
                notification = "",
                notificationShown = false
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application: UnscrambleApplication =
                    (this[APPLICATION_KEY] as UnscrambleApplication)
                val listOfWordsRepository: ListOfWordsRepository =
                    application.container.listOfWordsRepository
                val playersRepository: PlayersRepository = application.container.playersRepository
                GameScreenViewModel(
                    listOfWordsRepository = listOfWordsRepository,
                    playersRepository = playersRepository
                )
            }
        }
    }
}