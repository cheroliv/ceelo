package game.ceelo.entities

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase.CONFLICT_FAIL
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import game.ceelo.Constant.ONE
import game.ceelo.Constant.PLAYER_ONE_NAME
import game.ceelo.Constant.PLAYER_TWO_NAME
import game.ceelo.Constant.TWO
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
        fun findOne(id: Int): PlayerEntity

        @Query("SELECT COUNT(*) FROM Player")
        fun count(): Int

        @Query("SELECT * FROM Player")
        fun all(): List<PlayerEntity>

        companion object {
            fun SupportSQLiteDatabase.checkDefaultPlayers() {
                "select * from Player"
                    .let(::query)
                    .count
                    .let {
                        when (it) {
                            0 -> {
                                setOf(
                                    PlayerEntity(ONE, PLAYER_ONE_NAME).contentValues,
                                    PlayerEntity(TWO, PLAYER_TWO_NAME).contentValues,
                                ).forEach {
                                    beginTransaction()
                                    insert("Player", CONFLICT_FAIL, it)
                                    setTransactionSuccessful()
                                    endTransaction()
                                    it.clear()
                                }
                            }
                        }
                    }
            }
        }

        private val PlayerEntity.contentValues
            get() = ContentValues(2).apply {
                put("id", id)
                put("login", login)
            }
    }
}
}
