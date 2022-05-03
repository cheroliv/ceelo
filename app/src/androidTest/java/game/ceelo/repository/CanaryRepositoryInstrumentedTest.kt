package game.ceelo.repository

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import game.ceelo.database.CeeloDataBase
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before


@RunWith(AndroidJUnit4::class)
class CanaryRepositoryInstrumentedTest {

    private lateinit var db: CeeloDataBase

    //private lateinit var gameRepository: GameRepository

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            CeeloDataBase::class.java
        ).build()
       // gameRepository = db.gameRepository()!!
    }

    @After
    fun destroy() = db.close()
    @Test
    fun canary_repository_test() {
        // Context of the app under test.
      //val appContext =
         // InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("game.ceelo", appContext.packageName)
    }
}