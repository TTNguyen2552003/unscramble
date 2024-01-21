package app.kotlin.unscramble.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.kotlin.unscramble.UnscrambleApplication
import app.kotlin.unscramble.data.Player
import app.kotlin.unscramble.data.PlayersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InputNameScreenViewModel(private val playersRepository: PlayersRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<Player> = MutableStateFlow(value = Player())
    val uiState: StateFlow<Player> = _uiState.asStateFlow()

    fun updatePlayerName(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(playerName = newName)
        }
    }

    fun savePlayer() {
        viewModelScope.launch(Dispatchers.IO) {
            playersRepository.record(player = _uiState.value)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application: UnscrambleApplication =
                    this[APPLICATION_KEY] as UnscrambleApplication
                val playersRepository = application.container.playersRepository
                InputNameScreenViewModel(playersRepository = playersRepository)
            }
        }
    }
}