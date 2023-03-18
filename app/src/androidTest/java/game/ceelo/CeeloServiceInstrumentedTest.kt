@file:Suppress(
    "NonAsciiCharacters", "TestFunctionName", "SpellCheckingInspection"
)

package game.ceelo

import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import game.ceelo.CeeLoApp.Companion.NUMBER_PLAYERS
import game.ceelo.Constant.CEELO_DICE_THROW_SIZE
import game.ceelo.Constant.ONE
import game.ceelo.Constant.PLAYER_ONE_NAME
import game.ceelo.Constant.PLAYER_TWO_NAME
import game.ceelo.Constant.SIX
import game.ceelo.Constant.TWO
import game.ceelo.Game.runDices
import game.ceelo.Playground.launchLocalGame
import game.ceelo.R.id.player_one_first_dice
import game.ceelo.entities.PlayerEntity
import game.ceelo.entities.PlayerEntity.PlayerDao.Companion.checkDefaultPlayers
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
                        assertEquals(0, db.query("select * from Player").count)
                        db.checkDefaultPlayers()
                    }
                }).build()
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
    fun test_checkDefaultPlayers() = Pair(
        listOf(
            PlayerEntity(ONE, PLAYER_ONE_NAME),
            PlayerEntity(TWO, PLAYER_TWO_NAME),
        ), get<Database>().playerDao().all(),
    ).run {
        assertEquals(first.count(), second.count())
        assertEquals(NUMBER_PLAYERS, second.count())
        first.forEachIndexed { i, it -> assertEquals(it, second[i]) }
    }

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