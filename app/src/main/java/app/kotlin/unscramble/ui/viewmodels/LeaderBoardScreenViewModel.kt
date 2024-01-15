package app.kotlin.unscramble.ui.viewmodels

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.kotlin.unscramble.data.Player
import app.kotlin.unscramble.di.UnscrambleWordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class TopTenPlayers(
    val theList: List<Player> = emptyList()
)

class LeaderBoardScreenViewModel(private val repository: UnscrambleWordRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(TopTenPlayers())
    val uiState: StateFlow<TopTenPlayers> = _uiState.asStateFlow()

    init {
        updateTheList()
    }

    private fun updateTheList() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(
                    theList = repository.getLeaderBoardData()
                )
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class LeaderBoardScreenViewModelFactory(private val repository: UnscrambleWordRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LeaderBoardScreenViewModel(repository) as T
    }
}