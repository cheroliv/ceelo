@file:Suppress("MemberVisibilityCanBePrivate")

package game.ceelo.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.Instant.ofEpochMilli
import java.time.ZoneId.systemDefault
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
    abstract fun dicesRunDao(): DicesRunEntity.DicesRunDao
    abstract fun gameDao(): GameEntity.GameDao
    abstract fun playerDao(): PlayerEntity.PlayerDao

    companion object {
        const val DB_NAME = "ceelo.db"

        @JvmStatic
        @TypeConverter
        fun fromZonedDateTime(value: ZonedDateTime?): Long? = value?.fZDT

        @JvmStatic
        @TypeConverter
        fun toZonedDateTime(value: Long?): ZonedDateTime? = value?.tZDT

        val ZonedDateTime.fZDT get() = toInstant()?.toEpochMilli()

        val Long.tZDT: ZonedDateTime?
            get() = ofEpochMilli(this)
                .atZone(systemDefault())
    }
}