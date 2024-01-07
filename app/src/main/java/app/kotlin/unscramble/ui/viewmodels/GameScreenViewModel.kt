package app.kotlin.unscramble.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

data class GameUiState(
    val score: Int = 0,
    val timeout: Int = 3,
    val currentQuiz: String = "",
    val isOver: Boolean = false,
    val gameStatus: GameStatus = GameStatus.PRE_GAME
)

enum class GameStatus {
    PRE_GAME,
    PLAY,
    OVER
}

class GameScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    private val listOfWord = listOf<String>("apple", "hind", "boat")

    init {
        updateQuiz(newQuiz = scrambleWord(word = listOfWord[Random.nextInt(listOfWord.size)]))
    }

    fun scrambleWord(word: String): String {
        val charArray: CharArray = word.toCharArray()
        while (charArray.toString() == word)
            charArray.shuffle()
        return charArray.toString()
    }

    fun updateQuiz(newQuiz: String) {
        _uiState.update { currentState ->
            currentState.copy(currentQuiz = newQuiz)
        }
    }

    fun updateScore(score: Int) {
        _uiState.update { currentState ->
            currentState.copy(score = _uiState.value.score + score)
        }
    }

    fun updateGameStatus(newStatus: GameStatus) {
        _uiState.update { currentState ->
            currentState.copy(gameStatus = newStatus)
        }
    }

    fun decreaseTime() {
        _uiState.update { currentState ->
            currentState.copy(timeout = _uiState.value.timeout - 1)
        }
    }

    fun resetGame(){
        
    }
}