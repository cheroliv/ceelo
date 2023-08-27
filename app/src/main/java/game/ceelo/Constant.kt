@file:Suppress("MemberVisibilityCanBePrivate")

package game.ceelo

object Constant{
    const val PLAYER_ONE_NAME = "Player"
    const val PLAYER_TWO_NAME = "Computer"
    const val GAME_TYPE = "LOCAL"

    const val ONE = 1
    const val TWO = 2
    const val THREE = 3
    const val FOUR = 4
    const val FIVE = 5
    const val SIX = 6
    const val CEELO_DICE_THROW_SIZE = 3
    const val NOT_A_TRIPLET = -1
    const val NOT_A_DOUBLET = 0

    const val AUTOMATIC_WIN_456_CASE = 6
    const val UNIFORM_TRIPLET_CASE = 5
    const val UNIFORM_DOUBLET_CASE = 4
    const val STRAIGHT_234_345_CASE = 3
    const val OTHER_DICE_RUN_CASE = 2
    const val AUTOMATIC_LOOSE_123_CASE = 1

    val FOUR_FIVE_SIX by lazy { listOf(FOUR, FIVE, SIX) }
    val ONE_TWO_THREE by lazy { listOf(ONE, TWO, THREE) }

    val ONE_ONE_ONE by lazy { listOf(ONE, ONE, ONE) }
    val TWO_TWO_TWO by lazy { listOf(TWO, TWO, TWO) }
    val THREE_THREE_THREE by lazy { listOf(THREE, THREE, THREE) }
    val FOUR_FOUR_FOUR by lazy { listOf(FOUR, FOUR, FOUR) }
    val FIVE_FIVE_FIVE by lazy { listOf(FIVE, FIVE, FIVE) }
    val SIX_SIX_SIX by lazy { listOf(SIX, SIX, SIX) }

    val UNIFORM_TRIPLETS by lazy {
        listOf(
            ONE_ONE_ONE,
            TWO_TWO_TWO,
            THREE_THREE_THREE,
            FOUR_FOUR_FOUR,
            FIVE_FIVE_FIVE,
            SIX_SIX_SIX
        )
    }

    val ONE_ONE_X by lazy { listOf(ONE, ONE) }
    val TWO_TWO_X by lazy { listOf(TWO, TWO) }
    val THREE_THREE_X by lazy { listOf(THREE, THREE) }
    val FOUR_FOUR_X by lazy { listOf(FOUR, FOUR) }
    val FIVE_FIVE_X by lazy { listOf(FIVE, FIVE) }
    val SIX_SIX_X by lazy { listOf(SIX, SIX) }


    val UNIFORM_DOUBLETS by lazy {
        listOf(
            ONE_ONE_X,
            TWO_TWO_X,
            THREE_THREE_X,
            FOUR_FOUR_X,
            FIVE_FIVE_X,
            SIX_SIX_X
        )
    }

    val TWO_THREE_FOUR by lazy { listOf(TWO, THREE, FOUR) }
    val THREE_FOUR_FIVE by lazy { listOf(THREE, FOUR, FIVE) }

    val STRAIGHT_TRIPLETS by lazy {
        listOf(
            TWO_THREE_FOUR,
            THREE_FOUR_FIVE,
        )
    }
}

