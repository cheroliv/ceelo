package game.ceelo.vm

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import game.ceelo.*
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

class GameViewModel(val gameService: GameService) : ViewModel() {

    private val _resultPair: MutableLiveData<List<Pair<GameResult, Int>>> = MutableLiveData()
    val resultPairList: LiveData<List<Pair<GameResult, Int>>> = _resultPair

    private val _resultVisibility: MutableLiveData<Int> = MutableLiveData()
    val resultVisibility: LiveData<Int> = _resultVisibility

    private val _diceGame: MutableLiveData<List<List<Int>>> =
        MutableLiveData(
            listOf(
                listOf(Constant.ONE, Constant.ONE, Constant.ONE),
                listOf(Constant.ONE, Constant.ONE, Constant.ONE)
            )
        )
    val diceGame: LiveData<List<List<Int>>> = _diceGame

    private val _games: MutableLiveData<List<List<List<Int>>>> = MutableLiveData()
    val games: LiveData<List<List<List<Int>>>> = _games

    private val _greetingVisibility: MutableLiveData<Int> = MutableLiveData()
    val greetingVisibility: LiveData<Int> = _greetingVisibility

    private val _greeting: MutableLiveData<String> = MutableLiveData()
    val greeting: LiveData<String> = _greeting

    fun onClickPlayButton() {
        //TODO: ici pour utiliser le service room
        _diceGame.value = listOf(Game.runDices(), Game.runDices())
        gameService.saveGame(_diceGame.value!!)
        _resultVisibility.value = View.VISIBLE
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
        _greetingVisibility.value = View.VISIBLE
    }

    fun onClickSignOutButton() {
        _greetingVisibility.value = View.GONE
    }

    fun playerThrow(
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
                    image.setImageResource(diceImages.getDiceImageFromDiceValue(game[i][j]))
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
                        0 -> resultPairList.value?.first()?.first
                        else -> resultPairList.value?.get(1)?.first
                    }!!
                )
            }
        }

        resultUI.mapIndexed { i, view ->
            resultPairList.observe(gameActivity) { result ->
                setTextViewResult(view, result[i].first, result[i].second)
            }
        }
    }

    private val diceImages
        get() = listOf(
            dice_face_one,
            dice_face_two,
            dice_face_three,
            dice_face_four,
            dice_face_five,
            dice_face_six,
        )
}