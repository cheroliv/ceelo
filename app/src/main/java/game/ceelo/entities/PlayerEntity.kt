package game.ceelo.entities

import androidx.room.*
import java.util.*

@Entity(
    tableName = "Player",
    indices = [
        Index(value = ["login"], unique = true),
    ]
)
data class PlayerEntity(
    @PrimaryKey
    val id: UUID,
    val login: String,
) {

    @Dao
    interface PlayerDao {
//        @Query("SELECT * FROM Player p Where p.id = id")
//        fun findOne(id:UUID): PlayerEntity

        @Query("SELECT count(*) FROM Player")
        fun count(): Long
    }
}