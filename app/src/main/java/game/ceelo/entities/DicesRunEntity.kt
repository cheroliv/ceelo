package game.ceelo.entities

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "DicesRun", indices = [
        Index(value = ["firstDice"]),
        Index(value = ["middleDice"]),
        Index(value = ["lastDice"])
    ]
)
data class DicesRunEntity(
    @PrimaryKey
    val diceThrowId: UUID,
    val gameId: UUID,
    val playerId: UUID,
    val firstDice: Int,
    val middleDice: Int,
    val lastDice: Int
){
    @Dao
    interface DicesRunDao
}

