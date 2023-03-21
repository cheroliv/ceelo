//@file:Suppress("MemberVisibilityCanBePrivate")

package game.ceelo

import android.content.Intent
import android.view.View.*
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import game.ceelo.*
import game.ceelo.CeeLoApp.Companion.NUMBER_PLAYERS
import game.ceelo.Constant.ONE
import game.ceelo.Game.secondPlayer
import game.ceelo.GameResult.*
import game.ceelo.GameViewModel.Companion.diceImages
import game.ceelo.Hand.compareHands
import game.ceelo.Hand.getDiceImageFromDiceValue
import game.ceelo.R.drawable.*
import game.ceelo.auth.LoginActivity
import game.ceelo.databinding.ActivityGameBinding
import game.ceelo.stats.ResultTableActivity


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

    companion object {
        val diceImages: List<Int>
            get() = listOf(
                dice_face_one,
                dice_face_two,
                dice_face_three,
                dice_face_four,
                dice_face_five,
                dice_face_six,
            )
    }
}

@Suppress("unused")
fun GameViewModel.onClickSignOutButton() {
    greetingVisibility.value = GONE
}

@Suppress("unused")
fun GameViewModel.onClickSignInButton() {
    greetingVisibility.value = VISIBLE
}

private fun GameViewModel.playerThrow(
    playerUI: List<ImageView>,
    list: List<Int>,
    resultUI: TextView,
    playerResult: GameResult
) = playerUI.forEachIndexed { i, view ->
    runDiceAnimation(view, list[i], diceImages)
}.run {
    setTextViewResult(
        resultUI,
        playerResult,
        resultVisibility.value!!
    )
}


fun GameViewModel.onClickPlayButton() {
    //TODO: ici pour utiliser le service room
    diceGame.value = listOf(Game.runDices(), Game.runDices())
    service.saveGame(diceGame.value!!)
    resultVisibility.value = VISIBLE
    games.value = service.allGames()
    resultPair.value = diceGame
        .value!!
        .first()
        .compareHands(
            diceGame
                .value!!
                .secondPlayer()
        ).run {
            listOf(
                this to when {
                    this == WIN || this == RERUN -> VISIBLE
                    else -> GONE
                }, when {
                    this == WIN -> LOOSE
                    this == LOOSE -> WIN
                    else -> RERUN
                } to when {
                    this == LOOSE || this == RERUN -> VISIBLE
                    else -> GONE
                }
            )
        }
}

fun GameViewModel.loadLocalGame(activity: GameActivity) =
    activity.binding.apply {
        resultTableButtonOnClickListener(activity)
        signinButtonOnClickListener(activity)
        playersUIObserver(activity, this)
        playLocalButtonClickListener(this)
        resultUIObserver(activity, this)
    }

fun GameViewModel.resultUIObserver(
    activity: GameActivity,
    binding: ActivityGameBinding
) = binding.resultUI.forEachIndexed { i, view ->
    resultPair.observe(activity) { result ->
        setTextViewResult(view, result[i].first, result[i].second)
    }
}

fun GameViewModel.playLocalButtonClickListener(
    binding: ActivityGameBinding,
) = binding.playLocalButton.setOnClickListener {
    onClickPlayButton()
    binding.resultUI.forEachIndexed { i, view ->
        playerThrow(
            binding.playersUI[i],
            diceGame.value!![i],
            view,
            when (i) {
                0 -> resultPair.value?.first()?.first
                else -> resultPair.value?.get(1)?.first
            }!!
        )
    }
}

fun GameViewModel.playersUIObserver(
    activity: GameActivity,
    binding: ActivityGameBinding
) = diceGame.observe(activity) { game ->
    binding.playersUI.mapIndexed { i, images ->
        images.mapIndexed { j, image ->
            image.setImageResource(
                diceImages.getDiceImageFromDiceValue(
                    game[i][j]
                )
            )
        }
    }
}

fun ActivityGameBinding.signinButtonOnClickListener(activity: GameActivity) =
    signinButton.setOnClickListener {
        activity.startActivity(
            Intent(
                activity,
                LoginActivity::class.java
            )
        )
    }

fun ActivityGameBinding.resultTableButtonOnClickListener(activity: GameActivity) {
    resultTableButton.setOnClickListener {
        activity.startActivity(
            Intent(
                activity,
                ResultTableActivity::class.java
            )
        )
    }
}