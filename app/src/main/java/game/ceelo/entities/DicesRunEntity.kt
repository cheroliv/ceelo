package game.ceelo.entities

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import game.ceelo.Constant.ONE
import game.ceelo.Constant.TWO
import game.ceelo.Game

@Entity(
    tableName = "DicesRun", indices = [
        Index(value = ["firstDice"]),
        Index(value = ["middleDice"]),
        Index(value = ["lastDice"])
    ]
)
data class DicesRunEntity(
    @PrimaryKey
    val diceThrowId: Int,
    val gameId: Int,
    val playerId: Int,
    val firstDice: Int,
    val middleDice: Int,
    val lastDice: Int
) {

    fun foo() {
        val diceGame = listOf(Game.runDices(), Game.runDices())
        //hook post construct to create user and system and save their UUID inmemory
        //retrieve
        val playerEntities = Pair(
            PlayerEntity(ONE, "user"),
            PlayerEntity(TWO, "system")
        )
        val dicesRunEntity = DicesRunEntity(
            (ONE.. Int.MAX_VALUE).random(),
            (ONE.. Int.MAX_VALUE).random(),
            playerEntities.first.id,
            diceGame.first().first(),
            diceGame.first()[1],
            diceGame.first()[2],
        )

        val gameEntity = GameEntity(
            (ONE.. Int.MAX_VALUE).random(),
            playerEntities.first.id,
            false,
            true)
    }

    @Dao
    interface DicesRunDao
}

