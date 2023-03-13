package game.ceelo

import android.app.Application
import androidx.room.Room.databaseBuilder
import game.ceelo.auth.AuthentificationService
import game.ceelo.auth.AuthentificationServiceRest
import game.ceelo.entities.CeeloDatabase
import game.ceelo.entities.CeeloDatabase.Constants.DB_NAME
import game.ceelo.entities.DicesRunEntity.DicesRunDao
import game.ceelo.entities.GameEntity.GameDao
import game.ceelo.entities.PlayerEntity.PlayerDao
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


class CeeLoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CeeLoApp)
            modules(module {
                singleOf(::CeeloServiceAndroid) { bind<CeeloService>() }
                viewModelOf(::GameViewModel)
                singleOf<CeeloDatabase> {
                    databaseBuilder(
                        get(),
                        CeeloDatabase::class.java,
                        DB_NAME
                    ).build()
                }
                singleOf<GameDao> { get<CeeloDatabase>().gameDao() }
                singleOf<DicesRunDao> { get<CeeloDatabase>().dicesRunDao() }
                singleOf<PlayerDao> { get<CeeloDatabase>().playerDao() }
                singleOf(::AuthentificationServiceRest) { bind<AuthentificationService>() }
            })
        }

    }

}

