@file:Suppress("MemberVisibilityCanBePrivate")

package game.ceelo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import game.ceelo.*
import game.ceelo.CeeLoApp.Companion.NUMBER_PLAYERS
import game.ceelo.Constant.ONE
import game.ceelo.R.drawable.*


class GameViewModel(val service: CeeloService) : ViewModel() {
    internal val resultPair: MutableLiveData<List<Pair<GameResult, Int>>> = MutableLiveData()
    internal val resultVisibility: MutableLiveData<Int> = MutableLiveData()
    internal val games: MutableLiveData<List<List<List<Int>>>> = MutableLiveData()
    internal val greetingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val greeting: MutableLiveData<String> = MutableLiveData()
    internal val diceGame: MutableLiveData<List<List<Int>>> =
        MutableLiveData(mutableListOf<List<Int>>().apply {
            repeat(NUMBER_PLAYERS) { add(listOf(ONE, ONE, ONE)) }
        })
}
