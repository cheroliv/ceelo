@file:Suppress("UNUSED_VARIABLE")

package game.ceelo

import android.content.Intent
import game.ceelo.GameResult.*
import game.ceelo.R.drawable.*
import game.ceelo.databinding.ActivityGameBinding

val ActivityGameBinding.playersUI
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

val ActivityGameBinding.resultUI get() = listOf(localPlayerResult, computerResult)

fun ActivityGameBinding.loadLocalGame(
    gameActivity: GameActivity,
    gameViewModel: GameViewModel
) = apply {
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

    gameViewModel.apply {
        diceGame.observe(gameActivity) { game ->
            playersUI.mapIndexed { i, it ->
                playerUI(game[i], diceImages, it)
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
}
