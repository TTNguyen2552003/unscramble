package app.kotlin.unscramble.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class Player(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "player_name") val playerName: String = "Unknown",
    val score: Int = 0
)

@Dao
interface PlayerDao {
    @Query("SELECT * FROM Player Player ORDER BY score DESC LIMIT 10")
    fun getLeaderBoardData(): List<Player>

    @Insert
    fun createPlayer(player: Player)
}

@Database(
    entities = [Player::class],
    version = 1,
    exportSchema = false
)
abstract class PlayerDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}