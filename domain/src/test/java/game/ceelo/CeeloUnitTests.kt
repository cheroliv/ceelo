@file:Suppress("unused","NonAsciiCharacters")

package game.ceelo

import game.ceelo.Constant.AUTOMATIC_LOOSE_123_CASE
import game.ceelo.Constant.AUTOMATIC_WIN_456_CASE
import game.ceelo.Constant.CEELO_DICE_THROW_SIZE
import game.ceelo.Constant.FIVE
import game.ceelo.Constant.FIVE_FIVE_FIVE
import game.ceelo.Constant.FOUR
import game.ceelo.Constant.FOUR_FIVE_SIX
import game.ceelo.Constant.FOUR_FOUR_FOUR
import game.ceelo.Constant.NOT_A_DOUBLET
import game.ceelo.Constant.NOT_A_TRIPLET
import game.ceelo.Constant.ONE
import game.ceelo.Constant.ONE_ONE_ONE
import game.ceelo.Constant.ONE_TWO_THREE
import game.ceelo.Constant.OTHER_DICE_RUN_CASE
import game.ceelo.Constant.SIX
import game.ceelo.Constant.SIX_SIX_SIX
import game.ceelo.Constant.STRAIGHT_234_345_CASE
import game.ceelo.Constant.STRAIGHT_TRIPLETS
import game.ceelo.Constant.THREE
import game.ceelo.Constant.THREE_THREE_THREE
import game.ceelo.Constant.TWO
import game.ceelo.Constant.TWO_TWO_TWO
import game.ceelo.Constant.UNIFORM_DOUBLET_CASE
import game.ceelo.Constant.UNIFORM_TRIPLETS
import game.ceelo.Constant.UNIFORM_TRIPLET_CASE
import game.ceelo.Game.randomNumberOfPlayers
import game.ceelo.Game.runDices
import game.ceelo.GameResult.*
import game.ceelo.Hand.compareHands
import game.ceelo.Hand.handCase
import game.ceelo.Hand.handsOnSameCase
import game.ceelo.Hand.is123
import game.ceelo.Hand.is456
import game.ceelo.Hand.isStraight
import game.ceelo.Hand.isUniformDoublet
import game.ceelo.Hand.isUniformTriplet
import game.ceelo.Hand.uniformDoubletValue
import game.ceelo.Hand.uniformTripletValue
import game.ceelo.Playground.launchLocalGame
import game.ceelo.Playground.runConsoleLocalGame
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse


class CeeloUnitTests {

//    fun initPlayground(
//        @Suppress("UNUSED_PARAMETER") howMuchPlayer: Int
//    ): Playground = Playground(mutableListOf())

    @Test
    fun runTestAsMain(): Unit = println("un jet de dés :").also {
        runConsoleLocalGame()
    }

    @Test
    fun `Si le jet est correct alors la propriété dicesThrow renvoi un triplet d'entier entre 1 et 6`() =
        runDices().run {
            assertEquals(CEELO_DICE_THROW_SIZE, size)
            forEach { assert(it in ONE..SIX) }
        }

    @Test
    fun `Si le jet contient (4,5,6) alors la propriété is456 renvoi true`() {
        assert(listOf(4, 5, 6).is456)
        assert(listOf(4, 6, 5).is456)
        assert(listOf(5, 4, 6).is456)
        assert(listOf(5, 6, 4).is456)
        assert(listOf(6, 5, 4).is456)
        assert(listOf(6, 4, 5).is456)
    }

    @Test
    fun `Si le jet ne contient pas (4,5,6) alors la propriété is456 renvoi false`() {
        assertFalse(ONE_TWO_THREE.is456)
        UNIFORM_TRIPLETS.forEach { assertFalse(it.is456) }
    }

    @Test
    fun `Si le jet contient (1,2,3) alors la propriété is123 renvoi true`() {
        assert(listOf(1, 2, 3).is123)
        assert(listOf(1, 3, 2).is123)
        assert(listOf(2, 1, 3).is123)
        assert(listOf(2, 3, 1).is123)
        assert(listOf(3, 2, 1).is123)
        assert(listOf(3, 1, 2).is123)
    }

    @Test
    fun `Si le jet ne contient pas (1,2,3) alors la propriété is123 renvoi false`() {
        assertFalse(ONE_TWO_THREE.is456)
        UNIFORM_TRIPLETS.forEach { assertFalse(it.is123) }
    }

    @Test
    fun `Si le jet est un triplet uniforme alors la propriété isUniformTriplet renvoi un true`() {
        assert(ONE_ONE_ONE.isUniformTriplet)
        assert(TWO_TWO_TWO.isUniformTriplet)
        assert(THREE_THREE_THREE.isUniformTriplet)
        assert(FOUR_FOUR_FOUR.isUniformTriplet)
        assert(FIVE_FIVE_FIVE.isUniformTriplet)
        assert(SIX_SIX_SIX.isUniformTriplet)
        assertFalse(FOUR_FIVE_SIX.isUniformTriplet)
        assertFalse(ONE_TWO_THREE.isUniformTriplet)
    }

    @Test
    fun `Si le jet est un triplet uniforme alors la propriété uniformTripletValue renvoi la valeur facial du dé`() {
        assertEquals(NOT_A_TRIPLET, ONE_TWO_THREE.uniformTripletValue)
        assertEquals(ONE, ONE_ONE_ONE.uniformTripletValue)
        assertEquals(TWO, TWO_TWO_TWO.uniformTripletValue)
        assertEquals(THREE, THREE_THREE_THREE.uniformTripletValue)
        assertEquals(FOUR, FOUR_FOUR_FOUR.uniformTripletValue)
        assertEquals(FIVE, FIVE_FIVE_FIVE.uniformTripletValue)
        assertEquals(SIX, SIX_SIX_SIX.uniformTripletValue)
    }

    @Test
    fun `Si le jet comporte un doublet uniforme alors la propriété isUniformDoublet renvoi un true`() {
        assert(listOf(1, 1, 6).isUniformDoublet)
        assert(listOf(2, 2, 5).isUniformDoublet)
        assert(listOf(3, 3, 4).isUniformDoublet)
        assert(listOf(4, 4, 3).isUniformDoublet)
        assert(listOf(5, 5, 2).isUniformDoublet)
        assert(listOf(6, 6, 1).isUniformDoublet)
        assertFalse( FOUR_FIVE_SIX.isUniformDoublet)
        assertFalse(ONE_TWO_THREE.isUniformDoublet)
        UNIFORM_TRIPLETS.forEach { assertFalse(it.isUniformDoublet) }
    }

    @Test
    fun `Si le jet comporte un doublet uniforme alors la propriété uniformDoubletValue renvoi la valeur facial du dé non uniforme`() {
        assertEquals(SIX, listOf(1, 1, 6).uniformDoubletValue)
        assertEquals(FIVE, listOf(2, 2, 5).uniformDoubletValue)
        assertEquals(FOUR, listOf(3, 3, 4).uniformDoubletValue)
        assertEquals(THREE, listOf(4, 4, 3).uniformDoubletValue)
        assertEquals(TWO, listOf(5, 5, 2).uniformDoubletValue)
        assertEquals(ONE, listOf(6, 6, 1).uniformDoubletValue)
    }

    @Test
    fun `Si le jet ne comporte pas uniquement un doublet uniforme alors la propriété uniformDoubletValue renvoi la valeur NOT_A_DOUBLET`() {
        assertEquals(NOT_A_DOUBLET, ONE_TWO_THREE.uniformDoubletValue)
        assertEquals(NOT_A_DOUBLET, FOUR_FIVE_SIX.uniformDoubletValue)
        UNIFORM_TRIPLETS.forEach { assertEquals(NOT_A_DOUBLET, it.uniformDoubletValue) }
    }

    @Test
    fun `Si le jet contient (2,3,4) ou (3,4,5) alors la propriété isStraight renvoi true`() {
        assert(listOf(2, 3, 4).isStraight)
        assert(listOf(3, 4, 5).isStraight)
    }

    @Test
    fun `Si le jet ne contient pas (2,3,4) ou (3,4,5) alors la propriété isStraight renvoi false`() {
        UNIFORM_TRIPLETS.map {
            assertFalse(it.containsAll(STRAIGHT_TRIPLETS.first()))
            assertFalse(it.containsAll(STRAIGHT_TRIPLETS.last()))
        }
        assertFalse(STRAIGHT_TRIPLETS.map { it.containsAll(ONE_TWO_THREE) }.contains(true))
        assertFalse(STRAIGHT_TRIPLETS.map { it.containsAll(FOUR_FIVE_SIX) }.contains(true))
    }

    @Test
    fun `Si le jet contient (4,5,6) alors la propriété handCase renvoi AUTOMATIC_WIN_456_CASE`() =
        assertEquals(AUTOMATIC_WIN_456_CASE, listOf(4, 5, 6).handCase)

    @Test
    fun `Si le jet contient (1,2,3) alors la propriété handCase renvoi AUTOMATIC_LOOSE_123_CASE`() =
        assertEquals(AUTOMATIC_LOOSE_123_CASE, listOf(1, 2, 3).handCase)

    @Test
    fun `Si le jet contient (2,3,4) ou (3,4,5) alors la propriété handCase renvoi STRAIGHT_234_345_CASE`() {
        assertEquals(STRAIGHT_234_345_CASE, listOf(2, 3, 4).handCase)
        assertEquals(STRAIGHT_234_345_CASE, listOf(3, 4, 5).handCase)
    }

    @Test
    fun `Si le jet contient un triplet uniforme alors la propriété handCase renvoi TRIPLET_CASE`(): Unit =
        UNIFORM_TRIPLETS.forEach { assertEquals(UNIFORM_TRIPLET_CASE, it.handCase) }

    @Test
    fun `Si le jet contient un doublet uniforme uniquement alors la propriété handCase renvoi DOUBLET_CASE`() {
        assertEquals(UNIFORM_DOUBLET_CASE, listOf(1, 1, 6).handCase)
        assertEquals(UNIFORM_DOUBLET_CASE, listOf(2, 2, 5).handCase)
        assertEquals(UNIFORM_DOUBLET_CASE, listOf(3, 3, 4).handCase)
        assertEquals(UNIFORM_DOUBLET_CASE, listOf(4, 4, 3).handCase)
        assertEquals(UNIFORM_DOUBLET_CASE, listOf(5, 5, 2).handCase)
        assertEquals(UNIFORM_DOUBLET_CASE, listOf(6, 6, 1).handCase)
    }

    @Test
    fun `Si le jet ne contient ni (4,5,6) ni (1,2,3) ni triplet uniforme ni doublet uniforme alors la propriété handCase renvoi OTHERS_CASE`() =
        assertEquals(OTHER_DICE_RUN_CASE, listOf(1, 3, 6).handCase)

    @Test
    fun `Si le jet contient (4,5,6) et l'autre (1,2,3) alors la propriété compareHands renvoi WIN`() =
        assertEquals(WIN, FOUR_FIVE_SIX.compareHands(ONE_TWO_THREE))


    @Test
    fun `Si le jet contient (4,5,6) non ordonné et l'autre (1,2,3) alors la propriété compareHands renvoi WIN`() =
        assertEquals(WIN, listOf(5, 6, 4).compareHands(ONE_TWO_THREE))

    @Test
    fun `Si le jet contient (4,5,6) et l'autre aussi alors la propriété compareHands renvoi RETHROW`() =
        assertEquals(RERUN, FOUR_FIVE_SIX.compareHands(FOUR_FIVE_SIX))

    @Test
    fun `Si le jet contient (1,2,3) et l'autre (4,5,6) alors la propriété compareHands renvoi LOOSE`() =
        assertEquals(LOOSE, ONE_TWO_THREE.compareHands(FOUR_FIVE_SIX))

    @Test
    fun `Si le jet contient (1,2,3) non ordonné et l'autre non alors la propriété compareHands renvoi LOOSE`() =
        assertEquals(LOOSE, listOf(3, 2, 1).compareHands(FOUR_FIVE_SIX))

    @Test
    fun `Si le jet contient (1,2,3) et l'autre aussi alors la propriété compareHands renvoi RETHROW`() =
        assertEquals(RERUN, ONE_TWO_THREE.compareHands(ONE_TWO_THREE))


    @Test
    fun `Si le jet est un triplet uniforme et l'autre aussi avec une face plus faible alors la méthode handsOnSameCase renvoi WIN`() {
        SIX_SIX_SIX.run {
            assertEquals(WIN, handsOnSameCase(FIVE_FIVE_FIVE, handCase))
            assertEquals(WIN, handsOnSameCase(FOUR_FOUR_FOUR, handCase))
            assertEquals(WIN, handsOnSameCase(THREE_THREE_THREE, handCase))
            assertEquals(WIN, handsOnSameCase(TWO_TWO_TWO, handCase))
            assertEquals(WIN, handsOnSameCase(ONE_ONE_ONE, handCase))
        }
    }


    @Test
    fun `Si le jet est un triplet uniforme et l'autre aussi avec une face plus faible alors la propriété compareHands renvoi WIN`() {
        assertEquals(WIN, SIX_SIX_SIX.compareHands(FIVE_FIVE_FIVE))
        assertEquals( WIN,  SIX_SIX_SIX.compareHands(FOUR_FOUR_FOUR))
        assertEquals( WIN,  SIX_SIX_SIX.compareHands(THREE_THREE_THREE))
        assertEquals( WIN,  SIX_SIX_SIX.compareHands(TWO_TWO_TWO))
        assertEquals( WIN,  SIX_SIX_SIX.compareHands(ONE_ONE_ONE))
    }

    @Test
    fun `Si le jet est un triplet uniforme et l'autre aussi avec une face plus forte alors la propriété compareHands renvoi LOOSE`() {
        assertEquals( LOOSE,  ONE_ONE_ONE.compareHands(SIX_SIX_SIX))
        assertEquals( LOOSE,  ONE_ONE_ONE.compareHands(FIVE_FIVE_FIVE))
        assertEquals( LOOSE,  ONE_ONE_ONE.compareHands(FOUR_FOUR_FOUR))
        assertEquals( LOOSE,  ONE_ONE_ONE.compareHands(THREE_THREE_THREE))
        assertEquals( LOOSE,  ONE_ONE_ONE.compareHands(TWO_TWO_TWO))
    }

    @Test
    fun `Si le jet est un triplet uniforme et l'autre aussi avec la meme face alors la propriété compareHands renvoi RETHROW`() {
        assertEquals(SIX_SIX_SIX.compareHands(SIX_SIX_SIX), RERUN)
        assertEquals(FIVE_FIVE_FIVE.compareHands(FIVE_FIVE_FIVE), RERUN)
        assertEquals(FOUR_FOUR_FOUR.compareHands(FOUR_FOUR_FOUR), RERUN)
        assertEquals(THREE_THREE_THREE.compareHands(THREE_THREE_THREE), RERUN)
        assertEquals(TWO_TWO_TWO.compareHands(TWO_TWO_TWO), RERUN)
        assertEquals(ONE_ONE_ONE.compareHands(ONE_ONE_ONE), RERUN)
    }

    //TODO: tester les autres branches de cas pour

    /*
        - lancer une partie entre 2 et 6 joueurs
        -
     */
    @Test
    fun random_nombre_de_joueurs_est_inf_ou_egal_a_6_et_sup_ou_egal_a_2() {
        assert(randomNumberOfPlayers() <= SIX)
        assert(randomNumberOfPlayers() >= TWO)
    }


    @Test
    fun `launchLocalGame renvoi un game multi joueurs`() {
        val numberOfPlayer = randomNumberOfPlayers()
        val result = launchLocalGame(numberOfPlayer)
        println(numberOfPlayer)
        println(result)
        assertEquals(numberOfPlayer, result.size)
        result.map { hands ->
            assertEquals(THREE, hands.size)
            hands.map { dice -> assert(dice in ONE..SIX) }
        }
    }

    @Test
    fun `compareHands renvoi la liste des vainqueurs d'une partie`() {
        val numberOfPlayer: Int = randomNumberOfPlayers()
        val result: List<List<Int>> = launchLocalGame(numberOfPlayer)
        println(numberOfPlayer)
        println(result)
        assertEquals(numberOfPlayer, result.size)

        val winnerIndexes: MutableList<Int> = mutableListOf(0)
        result.forEachIndexed { index, hands: List<Int> ->
            if (index > 0) {
                if (hands.compareHands(result[index - 1]) == WIN)
                    winnerIndexes[0] = index
                if (
                    hands.compareHands(result[index - 1]) == RERUN
                    && hands.compareHands(result.first()) == WIN
                ) winnerIndexes.add(index)
            }
        }
        println("winner: ${winnerIndexes.map { result[it] }}")

        //construisons des situations facile a tester
//        val strongerFirst=
    }
}