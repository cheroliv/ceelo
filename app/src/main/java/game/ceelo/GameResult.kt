package game.ceelo

sealed class GameResult {
    object WIN : GameResult()
    object LOOSE : GameResult()
    object RERUN : GameResult()

    override fun toString() = when (this) {
        WIN -> "WIN"
        LOOSE -> "LOOSE"
        else -> "RERUN"
    }
}
//data class DiceRun(
//    val firstDice: Int,
//    val secondDice: Int,
//    val thirdDice: Int,
//    val player: Player,
//)
//data class Player(val name:String)
//class Game(val hands:List<DiceRun>)
//class Playground(val games:List<Game>)