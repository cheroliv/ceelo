package game.ceelo

import game.ceelo.Game.runDices
import game.ceelo.CeeloServiceInMemory.InMemoryData.addGame
import game.ceelo.CeeloServiceInMemory.InMemoryData.getAllGames

class CeeloServiceInMemory : CeeloService {
    private object InMemoryData {
        private val repo: MutableList<List<List<Int>>> by lazy {
            MutableList(0) { mutableListOf(runDices(), runDices()) }
        }

        @JvmStatic
        fun getAllGames(): List<List<List<Int>>> = repo

        @JvmStatic
        fun addGame(game: List<List<Int>>) {
            repo.add(game)
        }
    }

    override fun allGames(): List<List<List<Int>>> = getAllGames()
    override fun saveGame(newGame: List<List<Int>>) = addGame(newGame)
    override fun connect() {
        TODO("Not yet implemented")
    }

    override fun subscribe() {
        TODO("Not yet implemented")
    }
}