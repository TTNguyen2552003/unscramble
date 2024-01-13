package app.kotlin.unscramble.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Player(
    val name: String = "Unknown",
    val score: Int = 0
)

data class TopTenPlayers(
    val theList: List<Player> = emptyList()
)

class LeaderBoardScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TopTenPlayers())
    val uiState: StateFlow<TopTenPlayers> = _uiState.asStateFlow()

    private val samplePlayer = listOf<Player>(
        Player(name = "Andree", score = 80),
        Player(name = "Biden", score = 70),
        Player(name = "Trump", score = 60),
        Player(name = "Bill", score = 50),
        Player(name = "Steve", score = 40),
        Player(name = "Balmer", score = 20),
        Player(name = "Julia", score = 10),
        Player(name = "Stephen", score = 8)
    )

    init {
        updateTheList()
    }
    private fun updateTheList() {
        _uiState.update {
            currentState->currentState.copy(
                theList = samplePlayer
            )
        }
    }
}