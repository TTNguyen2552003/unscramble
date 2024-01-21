package app.kotlin.unscramble.data

import android.content.Context
import androidx.room.Room

interface PlayersRepository {
    fun record(player: Player)
    fun getLeaderBoardData(): List<Player>

    fun getTheLatestPlayer(): List<Player>
}

class LocalPlayersRepository(context: Context) : PlayersRepository {
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
        val playerDao: PlayerDao = db.playerDao()
        return playerDao.getLeaderBoardData()
    }

    override fun getTheLatestPlayer(): List<Player> {
        val playerDao: PlayerDao = db.playerDao()
        return playerDao.getLatestPlayer()
    }
}