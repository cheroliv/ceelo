package game.ceelo.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dice")
data class DicesThrow(
    @PrimaryKey(autoGenerate = true)
    val diceThrowId: Long,
    val gameId:Long,
    val playerId:Long,
    val firstDice: Int,
    val middleDice: Int,
    val lastDice: Int
)
/*
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "user",
    indices = [
        Index(value = ["login"], unique = true),
        Index(value = ["email"], unique = true)
    ]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Long? = null,
    val login: String,
    val email: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "date_of_birth")
    val dob: Date,
    val password: String,
)
 */