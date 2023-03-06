package game.ceelo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import game.ceelo.*
import game.ceelo.Constant.ONE
import game.ceelo.R.drawable.*

private const val NUMBER_PLAYERS = 2

class GameViewModel(
    @Suppress("MemberVisibilityCanBePrivate")
    val gameService: GameService
) : ViewModel() {
    internal val resultPair: MutableLiveData<List<Pair<GameResult, Int>>> = MutableLiveData()
    internal val resultVisibility: MutableLiveData<Int> = MutableLiveData()
    internal   val games: MutableLiveData<List<List<List<Int>>>> = MutableLiveData()
    internal  val greetingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val greeting: MutableLiveData<String> = MutableLiveData()
    internal  val diceGame: MutableLiveData<List<List<Int>>> =
        MutableLiveData(mutableListOf<List<Int>>().apply {
            repeat(NUMBER_PLAYERS) { add(listOf(ONE, ONE, ONE)) }
        })
}
