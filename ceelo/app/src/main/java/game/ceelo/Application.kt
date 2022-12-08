package game.ceelo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

internal val ceeloServiceInMemory: CeeloService by lazy {
    CeeloServiceInMemory()
}

val ceeloModule = module {
    single<CeeloService> { CeeloServiceInMemory() }
    viewModel{GameViewModel(get())}
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


