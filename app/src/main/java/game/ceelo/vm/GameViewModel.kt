package game.ceelo.vm

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import game.ceelo.*
import game.ceelo.Constant.ONE
import game.ceelo.Game.secondPlayer
import game.ceelo.Hand.compareHands
import game.ceelo.Hand.getDiceImageFromDiceValue
import game.ceelo.R.drawable.*
import game.ceelo.auth.LoginActivity
import game.ceelo.controller.playersUI
import game.ceelo.controller.resultUI
import game.ceelo.controller.runDiceAnimation
import game.ceelo.controller.setTextViewResult
import game.ceelo.databinding.ActivityGameBinding
import game.ceelo.stats.ResultTableActivity


class GameViewModel(
    @Suppress("MemberVisibilityCanBePrivate")
    val gameService: GameService
) : ViewModel() {
    companion object {
        private const val NUMBER_PLAYERS = 2
    }

    private val resultPair: MutableLiveData<List<Pair<GameResult, Int>>> = MutableLiveData()
    private val resultVisibility: MutableLiveData<Int> = MutableLiveData()
    private val games: MutableLiveData<List<List<List<Int>>>> = MutableLiveData()
    private val greetingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val greeting: MutableLiveData<String> = MutableLiveData()
    private val diceGame: MutableLiveData<List<List<Int>>> =
        MutableLiveData(mutableListOf<List<Int>>().apply {
            repeat(NUMBER_PLAYERS) { add(listOf(ONE, ONE, ONE)) }
        })

    private val diceImages: List<Int>
        get() = listOf(
            dice_face_one,
            dice_face_two,
            dice_face_three,
            dice_face_four,
            dice_face_five,
            dice_face_six,
        )

    private fun playerThrow(
        playerUI: List<ImageView>,
        list: List<Int>,
        resultUI: TextView,
        playerResult: GameResult
    ) = playerUI.mapIndexed { i, view ->
        runDiceAnimation(view, list[i], diceImages)
    }.run {
        setTextViewResult(
            resultUI,
            playerResult,
            resultVisibility.value!!
        )
    }


    fun loadLocalGame(
        gameActivity: GameActivity,
        binding: ActivityGameBinding
    ) = binding.apply {
        resultTableButton.setOnClickListener {
            gameActivity.startActivity(
                Intent(
                    gameActivity,
                    ResultTableActivity::class.java
                )
            )
        }

        signinButton.setOnClickListener {
            gameActivity.startActivity(
                Intent(
                    gameActivity,
                    LoginActivity::class.java
                )
            )
        }

        diceGame.observe(gameActivity) { game ->
            playersUI.mapIndexed { i, images ->
                images.mapIndexed { j, image ->
                    image.setImageResource(
                        diceImages.getDiceImageFromDiceValue(
                            game[i][j]
                        )
                    )
                }
            }
        }

        playLocalButton.setOnClickListener {
            onClickPlayButton()
            resultUI.mapIndexed { i, view ->
                playerThrow(
                    playersUI[i],
                    diceGame.value!![i],
                    view,
                    when (i) {
                        0 -> resultPair.value?.first()?.first
                        else -> resultPair.value?.get(1)?.first
                    }!!
                )
            }
        }

        resultUI.mapIndexed { i, view ->
            resultPair.observe(gameActivity) { result ->
                setTextViewResult(view, result[i].first, result[i].second)
            }
        }
    }

    fun onClickPlayButton() {
        //TODO: ici pour utiliser le service room
        diceGame.value = listOf(Game.runDices(), Game.runDices())
        gameService.saveGame(diceGame.value!!)
        resultVisibility.value = View.VISIBLE
        games.value = gameService.allGames()
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
                        this == GameResult.WIN || this == GameResult.RERUN -> View.VISIBLE
                        else -> View.GONE
                    }, when {
                        this == GameResult.WIN -> GameResult.LOOSE
                        this == GameResult.LOOSE -> GameResult.WIN
                        else -> GameResult.RERUN
                    } to when {
                        this == GameResult.LOOSE || this == GameResult.RERUN -> View.VISIBLE
                        else -> View.GONE
                    }
                )
            }
    }


    fun onClickSignInButton() {
        greetingVisibility.value = View.VISIBLE
    }

    fun onClickSignOutButton() {
        greetingVisibility.value = View.GONE
    }
}