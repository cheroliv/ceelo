@file:Suppress("MemberVisibilityCanBePrivate")

package game.ceelo.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import game.ceelo.entities.CeeloDatabase.TypeUtils.fromDateTime
import game.ceelo.entities.CeeloDatabase.TypeUtils.toDateTime
import game.ceelo.entities.CeeloDatabase.TypesConverter
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@Database(
    entities = [
        DicesRunEntity::class,
        GameEntity::class,
        PlayerEntity::class
    ], version = 1
)
@TypeConverters(TypesConverter::class)
abstract class CeeloDatabase : RoomDatabase() {
    abstract fun dicesRunDao(): DicesRunEntity.DicesRunDao
    abstract fun gameDao(): GameEntity.GameDao
    abstract fun playerDao(): PlayerEntity.PlayerDao

    object TypesConverter{
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
            get() = Instant.ofEpochMilli(this)
                .atZone(ZoneId.systemDefault())
    }

    object DATABASE {
        const val DB_NAME = "ceelo.db"
    }
}