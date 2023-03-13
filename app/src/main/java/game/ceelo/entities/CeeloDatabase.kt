@file:Suppress("MemberVisibilityCanBePrivate")

package game.ceelo.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import game.ceelo.entities.EntityUtils.fromDateTime
import game.ceelo.entities.EntityUtils.toDateTime
import java.time.ZonedDateTime

@Database(
    entities = [
        DicesRunEntity::class,
        GameEntity::class,
        PlayerEntity::class
    ], version = 1
)
@TypeConverters(CeeloDatabase::class)
abstract class CeeloDatabase : RoomDatabase() {
    object Constants {
        const val DB_NAME = "ceelo.db"
    }

    abstract fun dicesRunDao(): DicesRunEntity.DicesRunDao
    abstract fun gameDao(): GameEntity.GameDao
    abstract fun playerDao(): PlayerEntity.PlayerDao

    companion object {
        @JvmStatic
        @TypeConverter
        fun fromZonedDateTime(value: ZonedDateTime?): Long? = value?.fromDateTime

        @JvmStatic
        @TypeConverter
        fun toZonedDateTime(value: Long?): ZonedDateTime? = value?.toDateTime
    }
}