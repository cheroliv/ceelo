@file:Suppress(
    "NonAsciiCharacters", "TestFunctionName", "SpellCheckingInspection"
)

package game.ceelo

import android.util.Log.i
import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import game.ceelo.Constant.CEELO_DICE_THROW_SIZE
import game.ceelo.Constant.ONE
import game.ceelo.Constant.SIX
import game.ceelo.Game.runDices
import game.ceelo.Playground.launchLocalGame
import game.ceelo.R.id.player_one_first_dice
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule.Companion.create
import org.mockito.Mockito.mock
import java.math.BigInteger
import java.math.BigInteger.ZERO
import kotlin.test.Test
import kotlin.test.assertEquals


@RunWith(AndroidJUnit4::class)
class CeeloServiceInstrumentedTest : KoinTest {

    @get:Rule
    val mockProvider by lazy { create { clazz -> mock(clazz.java) } }

    private val ceeloService: CeeloService by inject()

    private val database: Database by inject()

    private val ceeloTest = module {
        singleOf(::CeeloServiceAndroid) { bind<CeeloService>() }
        viewModelOf(::GameViewModel)
        singleOf<Database> {
            inMemoryDatabaseBuilder(get(), Database::class.java)
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        i("foobar", "baztux")
                        val playerCount = db.query("select * from Player").count
                        i(CeeLoApp::class.java.simpleName, playerCount.toString())
//            if (playerCount == 0) {
//                db.run {
//                    beginTransaction()
//                    setOf(ContentValues().apply {
//                        put("id", UUID.randomUUID().toString())
//                        put("login", "user")
//                    }, ContentValues().apply {
//                        put("id", UUID.randomUUID().toString())
//                        put("login", "computer")
//                    }).map {
//                        insert("Player", CONFLICT_FAIL, it)
//                        it.clear()
//                    }
//                    endTransaction()
//                }
//            }
                    }
                })
                .build()
        }
    }

    @Before
    fun initService() = loadKoinModules(ceeloTest)

    @After
    fun after() {
        database.close()
        unloadKoinModules(ceeloTest)
    }

    @Test
    fun db_populate() {
        i(CeeLoApp::class.java.simpleName, get<Database>().playerDao().count().toString())
        assertEquals(0,get<Database>().playerDao().count().toInt())
//        assertEquals(NUMBER_PLAYERS,get<Database>().playerDao().count().toInt())
    }

    //    @org.junit.Ignore("TODO: too long! #DaftPunk")
    @Test
    fun ui_tests() {
        launch(GameActivity::class.java)
        androidx.test.espresso.Espresso.onView(withId(player_one_first_dice))
//            .check(androidx.test.espresso.assertion.ViewAssertions.matches(isCompletelyDisplayed()))
//            .check(androidx.test.espresso.assertion.ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun localDicesThrow_retourne_un_jeux_de_jet_de_dès_correct() {
        launchLocalGame().run {
            assertEquals(2, size)
            first().run hand@{
                assertEquals(CEELO_DICE_THROW_SIZE, this@hand.size)
                forEach { assert(it in ONE..SIX) }
            }
            last().run hand@{
                assertEquals(CEELO_DICE_THROW_SIZE, this@hand.size)
                forEach { assert(it in ONE..SIX) }
            }
        }
    }

    @Test
    fun allGames_retourne_toutes_les_parties_et_sont_correct() {
        ceeloService.allGames().forEach { game ->
            assertEquals(2, game.size)
            game.first().run {
                assertEquals(CEELO_DICE_THROW_SIZE, size)
                forEach { assert(it in (ONE..SIX)) }
            }
            game.last().run {
                assertEquals(CEELO_DICE_THROW_SIZE, size)
                forEach { assert(it in (ONE..SIX)) }
            }
        }
    }

    @Test
    fun saveGame_ajoute_une_partie() {
        ceeloService.allGames().size.run {
            ceeloService.saveGame(listOf(runDices(), runDices()))
            assertEquals(this + 1, ceeloService.allGames().size)
        }
    }
}