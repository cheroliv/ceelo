package game.ceelo

import android.content.Intent
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import game.ceelo.Game.runDices
import game.ceelo.Game.secondPlayer
import game.ceelo.GameResult.*
import game.ceelo.Hand.compareHands
import game.ceelo.Hand.getDiceImageFromDiceValue
import game.ceelo.R.drawable.*
import game.ceelo.auth.LoginActivity
import game.ceelo.databinding.ActivityGameBinding
import game.ceelo.stats.ResultTableActivity

private val diceImages: List<Int>
    get() = listOf(
        dice_face_one,
        dice_face_two,
        dice_face_three,
        dice_face_four,
        dice_face_five,
        dice_face_six,
    )

fun GameViewModel.loadLocalGame(gameActivity: GameActivity) = gameActivity
    .binding
    .apply {
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

fun GameViewModel.onClickPlayButton() {
    //TODO: ici pour utiliser le service room
    diceGame.value = listOf(runDices(), runDices())
    ceeloService.saveGame(diceGame.value!!)
    resultVisibility.value = VISIBLE
    games.value = ceeloService.allGames()
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

private fun GameViewModel.playerThrow(
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

private val GameActivity.binding: ActivityGameBinding
    get() = ActivityGameBinding.inflate(layoutInflater)
        .apply { setContentView(root) }

private val ActivityGameBinding.resultUI: List<TextView>
    get() = listOf(localPlayerResult, computerResult)

private val ActivityGameBinding.playersUI: List<List<ImageView>>
    get() = listOf(
        listOf(
            playerOneFirstDice,
            playerOneMiddleDice,
            playerOneLastDice
        ),
        listOf(
            playerTwoFirstDice,
            playerTwoMiddleDice,
            playerTwoLastDice
        )
    )


private fun setTextViewResult(
    textViewResult: TextView,
    diceResult: GameResult,
    textViewVisibility: Int
): TextView = textViewResult.apply {
    visibility = textViewVisibility
    text = when (diceResult) {
        WIN -> WIN.toString()
        LOOSE -> LOOSE.toString()
        else -> RERUN.toString()
    }
}

private fun runDiceAnimation(
    diceImage: ImageView,
    diceValue: Int,
    diceImages: List<Int>
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

@Suppress("unused")
fun GameViewModel.onClickSignOutButton() {
    greetingVisibility.value = GONE
}

@Suppress("unused")
fun GameViewModel.onClickSignInButton() {
    greetingVisibility.value = VISIBLE
}