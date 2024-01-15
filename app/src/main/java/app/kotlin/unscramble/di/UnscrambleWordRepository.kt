package app.kotlin.unscramble.di

import android.content.Context
import androidx.room.Room
import app.kotlin.unscramble.data.ListOfWords
import app.kotlin.unscramble.data.Player
import app.kotlin.unscramble.data.PlayerDao
import app.kotlin.unscramble.data.PlayerDatabase
import app.kotlin.unscramble.data.fetchData

interface Repository {
    suspend fun getTheListOfWord(): ListOfWords
    fun record(player: Player)

    fun getLeaderBoardData(): List<Player>
}

class UnscrambleWordRepository(context: Context) : Repository {
    override suspend fun getTheListOfWord(): ListOfWords {
        return fetchData()
    }

    private val db = Room.databaseBuilder(
        context = context,
        PlayerDatabase::class.java,
        name = "player_db.db"
    ).build()

    override fun record(player: Player) {
        val playerDao: PlayerDao = db.playerDao()
        playerDao.createPlayer(player)
    }

    override fun getLeaderBoardData(): List<Player> {
        val playerDao:PlayerDao = db.playerDao()
        return playerDao.getLeaderBoardData()
    }
}