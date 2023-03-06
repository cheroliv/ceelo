package game.ceelo

import game.ceelo.Constant.TWO
import game.ceelo.Hand.compareHands
import game.ceelo.Game.firstPlayer
import game.ceelo.Game.runDices
import game.ceelo.Game.secondPlayer
import game.ceelo.GameResult.RERUN
import game.ceelo.GameResult.WIN


object Playground {
    fun launchLocalGame(nbPlayers: Int): List<List<Int>> =
        mutableListOf<List<Int>>().apply {
            repeat(nbPlayers) { add(runDices()) }
        }.toList()

    fun launchLocalGame(): List<List<Int>> = launchLocalGame(TWO)

    fun runConsoleLocalGame() = launchLocalGame().toMutableList().run {
            do {
                println("player one throw : ${firstPlayer()}")
                println("player two throw : ${secondPlayer()}")
                val result = firstPlayer().compareHands(secondPlayer())
                when (result) {
                    WIN -> println("player one : $WIN")
                    else -> println("player two : $WIN")
                }
            } while (result == RERUN.apply {
                    this@run.run {
                        clear()
                        addAll(launchLocalGame())
                    }
                })
        }
}