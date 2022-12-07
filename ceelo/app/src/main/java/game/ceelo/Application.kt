package game.ceelo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


@JvmField
val ceeloModule = module {
    single<CeeloService> { CeeloServiceInMemory() }
    viewModel { DiceGameViewModel(get()) }
}
val ceeloService: CeeloService by lazy {
    CeeloServiceInMemory()
}

class CeeLoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CeeLoApplication)
            modules(ceeloModule)
        }
    }
}


