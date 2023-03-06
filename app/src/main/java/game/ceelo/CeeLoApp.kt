package game.ceelo

import android.app.Application
import game.ceelo.auth.ISecurityService
import game.ceelo.auth.SecurityService
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
                singleOf(::GameServiceAndroid) { bind<GameService>() }
                singleOf(::SecurityService) { bind<ISecurityService>()}
                viewModelOf(::GameViewModel)
            })
        }
    }
}


