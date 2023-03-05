package game.ceelo.sqlite

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "Game", indices = [
        Index("isDraw"),
        Index("isOffLineGame"),
    ]
)
data class GameEntity(
    @PrimaryKey
    val id: UUID,
    val winnerPlayerId: UUID,
    val date: Date,
    val isDraw: Boolean,
    val isOffLineGame: Boolean,
)