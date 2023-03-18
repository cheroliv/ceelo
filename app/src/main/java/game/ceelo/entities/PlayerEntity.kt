package game.ceelo.entities

import android.content.ContentValues
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
    val id: Int,
    val login: String,
) {

    @Dao
    interface PlayerDao {
        @Query("SELECT * FROM Player p Where p.id = :id")
        fun findOne(id:Int): PlayerEntity

        @Query("SELECT COUNT(*) FROM Player")
        fun count(): Int

        @Query("SELECT * FROM Player")
        fun all(): List<PlayerEntity>
    }

    val contentValues
        get() = ContentValues(2).apply {
            put("id", id)
            put("login", login)
        }
}