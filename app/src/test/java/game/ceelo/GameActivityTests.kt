package game.ceelo

import kotlin.test.*

class GameActivityTests {

    @BeforeTest
    fun setUp() {
        println("${this::class.java.simpleName}.setUp()")
    }

    @AfterTest
    fun tearDown() {
        println("${this::class.java.simpleName}.tearDown()")
    }

    @Test
    fun onCreate() {
        println("${this::class.java.simpleName}.onCreate()")
    }
}