package game.ceelo

import android.app.Application
import androidx.room.*
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import game.ceelo.CeeLoApp.Database.Companion.DB_NAME
import game.ceelo.CeeLoApp.Database.TypeUtils.fromDateTime
import game.ceelo.CeeLoApp.Database.TypeUtils.toDateTime
import game.ceelo.CeeLoApp.Database.TypesConverter
import game.ceelo.entities.DicesRunEntity
import game.ceelo.entities.DicesRunEntity.DicesRunDao
import game.ceelo.entities.GameEntity
import game.ceelo.entities.GameEntity.GameDao
import game.ceelo.entities.PlayerEntity
import game.ceelo.entities.PlayerEntity.PlayerDao
import game.ceelo.entities.PlayerEntity.PlayerDao.Companion.checkDefaultPlayers
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.time.Instant.ofEpochMilli
import java.time.ZoneId.systemDefault
import java.time.ZonedDateTime
import java.util.*


class CeeLoApp : Application() {
    companion object {
        const val NUMBER_PLAYERS = 2
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CeeLoApp)
            modules(module {
                singleOf(::CeeloServiceAndroid) { bind<CeeloService>() }
                viewModelOf(::GameViewModel)
                singleOf<Database> {
                    databaseBuilder(
                        get(),
                        Database::class.java,
                        DB_NAME
                    ).addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            db.run {
                                super.onCreate(this)
                                checkDefaultPlayers()
                            }
                        }
                    }).build()
                }
            })
        }
    }

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
}