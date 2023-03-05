@file:Suppress("UNUSED_VARIABLE")

package game.ceelo.controller

import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import game.ceelo.GameResult
import game.ceelo.GameResult.*
import game.ceelo.Hand.getDiceImageFromDiceValue
import game.ceelo.R.drawable.*
import game.ceelo.databinding.ActivityGameBinding


fun runDiceAnimation(
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

