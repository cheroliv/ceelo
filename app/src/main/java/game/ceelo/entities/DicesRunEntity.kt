package game.ceelo.entities

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import game.ceelo.Game
import java.util.*
import java.util.UUID.randomUUID

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
) {

    fun foo() {
        val diceGame = listOf(Game.runDices(), Game.runDices())
        //hook post construct to create user and system and save their UUID inmemory
        //retrieve
        val playerEntities = Pair(
            PlayerEntity(randomUUID(), "user"),
            PlayerEntity(randomUUID(), "system")
        )
        val dicesRunEntity = DicesRunEntity(
            randomUUID(),
            randomUUID(),
            playerEntities.first.id,
            diceGame.first().first(),
            diceGame.first()[1],
            diceGame.first()[2],
        )

        val gameEntity = GameEntity(
            randomUUID(),
            playerEntities.first.id,
            false,
            true)
    }

    @Dao
    interface DicesRunDao
}

