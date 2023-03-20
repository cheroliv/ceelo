package game.ceelo

import game.ceelo.CeeloServiceAndroid.InMemoryData.addGame
import game.ceelo.CeeloServiceAndroid.InMemoryData.getAllGames
import game.ceelo.Game.runDices

//TODO: add room, retrofit
class CeeloServiceAndroid(val database: Database) : CeeloServiceKtor() {
    object InMemoryData {
        private val repo: MutableList<List<List<Int>>> by lazy {
            MutableList(size = 0,
                init = { mutableListOf(runDices(), runDices()) })
        }

        @JvmStatic
        fun getAllGames(): List<List<List<Int>>> = repo//TODO: pagination circular buffer

        @JvmStatic
        fun addGame(game: List<List<Int>>) {
            with(repo) { add(game) }
        }
    }

    override fun allGames(): List<List<List<Int>>> = getAllGames()
    override fun saveGame(newGame: List<List<Int>>) = addGame(newGame)
}