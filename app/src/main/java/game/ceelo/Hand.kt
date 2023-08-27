@file:Suppress("MemberVisibilityCanBePrivate")

package game.ceelo

import game.ceelo.Constant.AUTOMATIC_LOOSE_123_CASE
import game.ceelo.Constant.AUTOMATIC_WIN_456_CASE
import game.ceelo.Constant.FIVE
import game.ceelo.Constant.FOUR
import game.ceelo.Constant.FOUR_FIVE_SIX
import game.ceelo.Constant.NOT_A_DOUBLET
import game.ceelo.Constant.NOT_A_TRIPLET
import game.ceelo.Constant.ONE
import game.ceelo.Constant.ONE_TWO_THREE
import game.ceelo.Constant.OTHER_DICE_RUN_CASE
import game.ceelo.Constant.SIX
import game.ceelo.Constant.STRAIGHT_234_345_CASE
import game.ceelo.Constant.STRAIGHT_TRIPLETS
import game.ceelo.Constant.THREE
import game.ceelo.Constant.THREE_FOUR_FIVE
import game.ceelo.Constant.TWO
import game.ceelo.Constant.TWO_THREE_FOUR
import game.ceelo.Constant.UNIFORM_DOUBLETS
import game.ceelo.Constant.UNIFORM_DOUBLET_CASE
import game.ceelo.Constant.UNIFORM_TRIPLETS
import game.ceelo.Constant.UNIFORM_TRIPLET_CASE
import game.ceelo.GameResult.*

object Hand {

    /**
     * Renvoi le dé du milieu
     */
    fun List<Int>.middleDice(): Int = if (isEmpty())
        throw NoSuchElementException("dice throw is empty.")
    else elementAt(index = ONE)

    val List<Int>.is456: Boolean get() = containsAll(FOUR_FIVE_SIX)

    val List<Int>.is123: Boolean get() = containsAll(ONE_TWO_THREE)


    fun List<Int>.firstDice(): Int = if (isEmpty())
        throw NoSuchElementException("no first dice found in hand.")
    else first()


    fun List<Int>.lastDice(): Int = if (isEmpty())
        throw NoSuchElementException("no last dice found in hand.")
    else last()


    /* Est ce un triplet? */
    val List<Int>.isUniformTriplet: Boolean
        get() = UNIFORM_TRIPLETS.map {
            it.firstDice().run {
                this == firstDice() &&
                        this == middleDice() &&
                        this == last()
            }
        }.contains(true)

    /**
     * La valeur faciale du dé triplet
     * Si le jet n'est pas un triplet
     * renvoi NOT_A_TRIPLET
     */
    val List<Int>.uniformTripletValue: Int
        get() = if (!isUniformTriplet) NOT_A_TRIPLET
        else UNIFORM_TRIPLETS.find { containsAll(elements = it) }!!.first()

    val List<Int>.isUniformDoublet: Boolean
        get() = run {
            UNIFORM_DOUBLETS.map {
                it.firstDice().run {
                    (firstDice() == this &&
                            middleDice() == this &&
                            lastDice() != this)
                            ||
                            (firstDice() == this &&
                                    lastDice() == this
                                    && middleDice() != this)
                            ||
                            (middleDice() == this &&
                                    lastDice() == this &&
                                    firstDice() != this)
                }
            }.contains(true)
        }

    /**
     * Renvoi la valeur du dé qui n'est pas un doublet
     */
    val List<Int>.uniformDoubletValue: Int
        get() = if (!isUniformTriplet && !isUniformDoublet) NOT_A_DOUBLET
        else when {
            isUniformTriplet -> NOT_A_DOUBLET
            isUniformDoublet -> find { dice: Int ->
                UNIFORM_DOUBLETS.first { hand: List<Int> ->
                    (firstDice() == hand.firstDice() &&
                            middleDice() == hand.firstDice())
                            ||
                            (firstDice() == hand.firstDice() &&
                                    lastDice() == hand.firstDice())
                            ||
                            (middleDice() == hand.firstDice() &&
                                    lastDice() == hand.firstDice())
                }.first() != dice
            }
            else -> NOT_A_DOUBLET
        }!!

    val List<Int>.isStraight: Boolean
        get() = STRAIGHT_TRIPLETS.map { containsAll(it) }.contains(true)

    fun List<Int>.getDiceImageFromDiceValue(
        diceValue: Int
    ): Int = when (diceValue) {
        ONE -> this[ONE - ONE]
        TWO -> this[TWO - ONE]
        THREE -> this[THREE - ONE]
        FOUR -> this[FOUR - ONE]
        FIVE -> this[FIVE - ONE]
        SIX -> this[SIX - ONE]
        else -> throw Exception("Only six faces is possible!")
    }

    val List<Int>.handCase: Int
        get() = when {
            is456 -> AUTOMATIC_WIN_456_CASE
            is123 -> AUTOMATIC_LOOSE_123_CASE
            isStraight -> STRAIGHT_234_345_CASE
            isUniformTriplet -> UNIFORM_TRIPLET_CASE
            isUniformDoublet -> UNIFORM_DOUBLET_CASE
            else -> OTHER_DICE_RUN_CASE
        }

    /**
     * compare un jet à un autre
     * pour renvoyer un resultat de jeu
     */
    fun List<Int>.compareHands(secondPlayerRun: List<Int>)
            : GameResult =
        handCase.run whichCase@{
            @Suppress("IMPLICIT_NOTHING_TYPE_ARGUMENT_AGAINST_NOT_NOTHING_EXPECTED_TYPE")
            secondPlayerRun.handCase.run otherWhichCase@{
                return when {
                    this@whichCase > this@otherWhichCase -> WIN
                    this@whichCase < this@otherWhichCase -> LOOSE
                    else -> handsOnSameCase(secondPlayerRun, this@whichCase)
                }
            }
        }
//TODO: RERUN [5,2,4][6,1,4] make biggest dice win
    fun List<Int>.handsOnSameCase(
        secondPlayerThrow: List<Int>,
        handCase: Int
    ): GameResult = if (handCase == AUTOMATIC_WIN_456_CASE) RERUN
    else if (handCase == AUTOMATIC_LOOSE_123_CASE) RERUN
    else if (handCase == STRAIGHT_234_345_CASE) {
        if (containsAll(TWO_THREE_FOUR)
            && secondPlayerThrow.containsAll(TWO_THREE_FOUR)
        ) RERUN
        else if (containsAll(THREE_FOUR_FIVE)
            && secondPlayerThrow.containsAll(THREE_FOUR_FIVE)
        ) RERUN
        else if (containsAll(TWO_THREE_FOUR)
            && secondPlayerThrow.containsAll(THREE_FOUR_FIVE)
        ) LOOSE
        else WIN
    } else if (handCase == UNIFORM_TRIPLET_CASE) {
        if (uniformTripletValue > secondPlayerThrow.uniformTripletValue) WIN
        else if (uniformTripletValue < secondPlayerThrow.uniformTripletValue) LOOSE
        else RERUN
    } else if (handCase == UNIFORM_DOUBLET_CASE) {
        if (uniformDoubletValue > secondPlayerThrow.uniformDoubletValue) WIN
        else if (uniformDoubletValue < secondPlayerThrow.uniformDoubletValue) LOOSE
        else RERUN
    } else if (sum() > secondPlayerThrow.sum()) WIN
    else if (sum() < secondPlayerThrow.sum()) LOOSE
    else RERUN
}

//    fun initBank(howMuchPlayer: Int): List<Int> = List(
//        size = howMuchPlayer,
//        init = { (ONE..SIX).random() }
//    )