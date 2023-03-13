package game.ceelo

import android.app.Application
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import game.ceelo.CeeLoApp.CeeloDatabase.DatabaseTypeConverters
import game.ceelo.auth.AuthentificationService
import game.ceelo.auth.AuthentificationServiceKtor
import game.ceelo.entities.DicesRunEntity
import game.ceelo.entities.DicesRunEntity.DicesRunDao
import game.ceelo.entities.GameEntity
import game.ceelo.entities.GameEntity.GameDao
import game.ceelo.entities.PlayerEntity
import game.ceelo.entities.PlayerEntity.PlayerDao
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidApplication
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


class CeeLoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CeeLoApp)
            modules(module {
                single {
                    databaseBuilder(
                        androidApplication(),
                        CeeloDatabase::class.java,
                        "ceelo.db"
                    ).build()
                }
                singleOf<GameDao> { get<CeeloDatabase>().gameDao() }
                singleOf<DicesRunDao> { get<CeeloDatabase>().dicesRunDao() }
                singleOf<PlayerDao> { get<CeeloDatabase>().playerDao() }
                singleOf(::CeeloServiceAndroid) { bind<CeeloService>() }
                viewModelOf(::GameViewModel)
                singleOf(::AuthentificationServiceKtor) { bind<AuthentificationService>() }
            })
        }

    }

    @Database(
        entities = [
            DicesRunEntity::class,
            GameEntity::class,
            PlayerEntity::class
        ], version = 1
    )
    @TypeConverters(DatabaseTypeConverters::class)
    abstract class CeeloDatabase : RoomDatabase() {
        abstract fun dicesRunDao(): DicesRunDao
        abstract fun gameDao(): GameDao
        abstract fun playerDao(): PlayerDao

        class DatabaseTypeConverters {
            companion object {

                @JvmStatic
                @TypeConverter
                fun fromZonedDateTime(value: ZonedDateTime?): Long? =
                    value
                        ?.toInstant()
                        ?.toEpochMilli()

                @JvmStatic
                @TypeConverter
                fun toZonedDateTime(value: Long?): ZonedDateTime? = value?.run {
                    ofEpochMilli(this)
                        .atZone(systemDefault())
                }
            }
        }


    }
}

