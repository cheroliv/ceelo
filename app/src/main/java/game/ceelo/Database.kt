@file:Suppress("MemberVisibilityCanBePrivate")

package game.ceelo

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import game.ceelo.Database.TypeUtils.fromDateTime
import game.ceelo.Database.TypeUtils.toDateTime
import game.ceelo.Database.TypesConverter
import game.ceelo.entities.DicesRunEntity
import game.ceelo.entities.DicesRunEntity.DicesRunDao
import game.ceelo.entities.GameEntity
import game.ceelo.entities.GameEntity.GameDao
import game.ceelo.entities.PlayerEntity
import game.ceelo.entities.PlayerEntity.PlayerDao
import java.time.Instant.ofEpochMilli
import java.time.ZoneId.systemDefault
import java.time.ZonedDateTime
import java.util.*
import android.util.Log.i



@androidx.room.Database(
    entities = [
        DicesRunEntity::class,
        GameEntity::class,
        PlayerEntity::class
    ], version = 1
)
@TypeConverters(TypesConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun dicesRunDao(): DicesRunDao
    abstract fun gameDao(): GameDao
    abstract fun playerDao(): PlayerDao

    object TypesConverter {
        @JvmStatic
        @TypeConverter
        fun fromZonedDateTime(value: ZonedDateTime?): Long? = value?.fromDateTime

        @JvmStatic
        @TypeConverter
        fun toZonedDateTime(value: Long?): ZonedDateTime? = value?.toDateTime
    }

    object TypeUtils {
        val ZonedDateTime.fromDateTime: Long? get() = toInstant()?.toEpochMilli()
        val Long.toDateTime: ZonedDateTime?
            get() = ofEpochMilli(this)
                .atZone(systemDefault())
    }

    companion object {
        const val DB_NAME = "ceelo.db"
    }
}


fun SupportSQLiteDatabase.addDefaultPlayers(){
    i("populate database","addDefaultPlayers")
    //            if (playerCount == 0) {
//                db.run {
//                    beginTransaction()
//                    setOf(ContentValues().apply {
//                        put("id", UUID.randomUUID().toString())
//                        put("login", "user")
//                    }, ContentValues().apply {
//                        put("id", UUID.randomUUID().toString())
//                        put("login", "computer")
//                    }).map {
//                        insert("Player", CONFLICT_FAIL, it)
//                        it.clear()
//                    }
//                    endTransaction()
//                }
//            }
}
