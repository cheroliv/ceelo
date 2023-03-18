package game.ceelo

import android.app.Application
import androidx.room.Room.databaseBuilder
import game.ceelo.Database.Companion.DB_NAME
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.util.*


class CeeLoApp : Application() {

    companion object {
        const val NUMBER_PLAYERS = 2
    }

    @Suppress("unused")
    private lateinit var userId: UUID

    @Suppress("unused")
    private lateinit var systemId: UUID

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
                    )
//                        .addCallback(object : Callback() {
//                            override fun onCreate(db: SupportSQLiteDatabase) {
//                                super.onCreate(db)
//                                Log.i("foobar", "baztux")
//                                val playerCount = db.query("select * from Player").count
//                                Log.i(CeeLoApp::class.java.simpleName, playerCount.toString())
//                            }
//                        })

                        .build()
                }
            })
        }
    }
//     val databasePostConstruct: Callback
//        get() = object : Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                Log.i("foobar", "baztux")
//                val playerCount = db.query("select * from Player").count
//                Log.i(CeeLoApp::class.java.simpleName, playerCount.toString())
//            }
//        }
//     val buildDatabase
//        get() = databaseBuilder(
//            get(),
//            Database::class.java,
//            DB_NAME
//        ).addCallback(databasePostConstruct)
//            .build()


}

