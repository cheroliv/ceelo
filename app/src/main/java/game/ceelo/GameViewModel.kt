@file:Suppress(
    "MemberVisibilityCanBePrivate",
    "unused"
)

package game.ceelo

import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import game.ceelo.Constant.ONE
import game.ceelo.Game.runDices
import game.ceelo.Game.secondPlayer
import game.ceelo.GameResult.*
import game.ceelo.Hand.compareHands
import game.ceelo.Hand.getDiceImageFromDiceValue
import game.ceelo.R.drawable.*

class GameViewModel(val gameService: GameService) : ViewModel() {
    val diceImages
        get() = listOf(
            dice_face_one,
            dice_face_two,
            dice_face_three,
            dice_face_four,
            dice_face_five,
            dice_face_six,
        )

    private val _resultPair: MutableLiveData<List<Pair<GameResult, Int>>> = MutableLiveData()
    val resultPairList: LiveData<List<Pair<GameResult, Int>>> = _resultPair

    private val _resultVisibility: MutableLiveData<Int> = MutableLiveData()
    val resultVisibility: LiveData<Int> = _resultVisibility

    private val _diceGame: MutableLiveData<List<List<Int>>> =
        MutableLiveData(listOf(listOf(ONE, ONE, ONE), listOf(ONE, ONE, ONE)))
    val diceGame: LiveData<List<List<Int>>> = _diceGame

    private val _games: MutableLiveData<List<List<List<Int>>>> = MutableLiveData()
    val games: LiveData<List<List<List<Int>>>> = _games

    private val _greetingVisibility: MutableLiveData<Int> = MutableLiveData()
    val greetingVisibility: LiveData<Int> = _greetingVisibility

    private val _greeting: MutableLiveData<String> = MutableLiveData()
    val greeting: LiveData<String> = _greeting

    fun onClickPlayButton() {
        //TODO: ici pour utiliser le service room
        _diceGame.value = listOf(runDices(), runDices())
        gameService.saveGame(_diceGame.value!!)
        _resultVisibility.value = VISIBLE
        _games.value = gameService.allGames()
        _resultPair.value = _diceGame
            .value!!
            .first()
            .compareHands(
                _diceGame
                    .value!!
                    .secondPlayer()
            ).run {
                listOf(
                    Pair(
                        this,
                        when {
                            this == WIN || this == RERUN -> VISIBLE
                            else -> GONE
                        }
                    ),
                    Pair(
                        when {
                            this == WIN -> LOOSE
                            this == LOOSE -> WIN
                            else -> RERUN
                        },
                        when {
                            this == LOOSE || this == RERUN -> VISIBLE
                            else -> GONE
                        }
                    )
                )
            }
    }

    fun onClickSignInButton() {
        _greetingVisibility.value = VISIBLE
    }

    fun onClickSignOutButton() {
        _greetingVisibility.value = GONE
    }
    fun playerThrow(
        playerUI: List<ImageView>,
        list: List<Int>,
        resultUI: TextView,
        playerResult: GameResult
    ) = playerUI.mapIndexed { i, view ->
        runDiceAnimation(view, list[i])
    }.run {
        setTextViewResult(
            resultUI,
            playerResult,
            resultVisibility.value!!
        )
    }

    fun playerUI(
        game: List<Int>,
        diceImages: List<Int>,
        images: List<ImageView>
    ) = images.mapIndexed { i, image ->
        image.setImageResource(diceImages.getDiceImageFromDiceValue(game[i]))
    }

    fun setTextViewResult(
        textViewResult: TextView,
        diceResult: GameResult,
        textViewVisibility: Int
    ) = textViewResult.apply {
        visibility = textViewVisibility
        text = when (diceResult) {
            WIN -> WIN.toString()
            LOOSE -> LOOSE.toString()
            else -> RERUN.toString()
        }
    }

    fun runDiceAnimation(
        diceImage: ImageView,
        diceValue: Int,
    ) = diceImage.apply {
        setImageResource(diceImages.getDiceImageFromDiceValue(diceValue))
    }.run {
        startAnimation(
            RotateAnimation(
                0f,
                360f,
                RELATIVE_TO_SELF,
                0.5f,
                RELATIVE_TO_SELF,
                0.5f
            ).apply { duration = 500 })
    }
}