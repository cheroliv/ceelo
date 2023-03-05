package game.ceelo.sqlite

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "Player", indices = [
        Index(value = ["login"], unique = true),
    ]
)
data class PlayerEntity(
    @PrimaryKey
    val id: UUID,
    val login: String,
)