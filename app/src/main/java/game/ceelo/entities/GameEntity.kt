package game.ceelo.entities

import androidx.room.*
import java.time.Instant
import java.time.Instant.ofEpochMilli
import java.time.ZoneId
import java.time.ZoneId.systemDefault
import java.time.ZonedDateTime
import java.time.ZonedDateTime.now
import java.util.*

@Entity(
    tableName = "Game", indices = [
        Index("isDraw"),
        Index("isOffLineGame"),
    ]
)
data class GameEntity(
    @PrimaryKey
    val id: Int,
    val winnerPlayerId: Int,
    val isDraw: Boolean,
    val isOffLineGame: Boolean,
    val date: ZonedDateTime = now(),
) {
    @Dao
    interface GameDao {
        // List<List<List<Int>>>
        @Query("SELECT * FROM Game")
        fun allGames(): List<GameEntity>

        //List<List<Int>>
        @Insert
        fun saveGame(newGame: GameEntity)
    }
}
