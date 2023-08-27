package game.ceelo

import game.ceelo.Constant.FIVE
import game.ceelo.Constant.FOUR
import game.ceelo.Constant.ONE
import game.ceelo.Constant.SIX
import game.ceelo.Constant.THREE
import game.ceelo.Constant.TWO

@Suppress("unused")
object Game {
    /**
     * un jet de dés au hazard
     */
    fun runDices(): List<Int> = List(size = 3, init = { (ONE..SIX).random() })

    fun randomNumberOfPlayers(): Int = (TWO..SIX).random()

    fun List<List<Int>>.firstPlayer(): List<Int> = if (isEmpty())
        throw NoSuchElementException("first player throw is empty.")
    else first()

    fun List<List<Int>>.secondPlayer(): List<Int> = if (isEmpty())
        throw NoSuchElementException("second player throw is empty.")
    else elementAt(index = ONE)


    fun List<List<Int>>.thirdPlayer(): List<Int> = if (isEmpty())
        throw NoSuchElementException("third player throw is empty.")
    else elementAt(index = TWO)

    fun List<List<Int>>.fourthPlayer(): List<Int> = if (isEmpty())
        throw NoSuchElementException("fourth player throw is empty.")
    else elementAt(index = THREE)

    fun List<List<Int>>.fifthPlayer(): List<Int> = if (isEmpty())
        throw NoSuchElementException("fifth player throw is empty.")
    else elementAt(index = FOUR)

    fun List<List<Int>>.sixthPlayer(): List<Int> = if (isEmpty())
        throw NoSuchElementException("sixth player throw is empty.")
    else elementAt(index = FIVE)
}
//    fun List<List<Int>>.compareHands(): List<List<Int>> {
////        var winner = first()
////        forEachIndexed { index: Int, hand ->
////            if (index >= ONE) {
////                val result = hand.compareThrows(this[index - 1])
////                if (result == WIN) {
////                    winner = hand
////                }
////            }
////        }
////        return winner
//        return listOf(listOf(0, 0))
//    }
