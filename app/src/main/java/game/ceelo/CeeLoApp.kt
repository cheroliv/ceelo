package game.ceelo

import android.app.Application
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import game.ceelo.Database.Companion.DB_NAME
import game.ceelo.entities.PlayerEntity.PlayerDao.Companion.checkDefaultPlayers
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

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
                            super.onCreate(db)
                            db.checkDefaultPlayers()
                        }
                    }).build()
                }
            })
        }
    }
}


