@file:Suppress("UNUSED_VARIABLE")

package game.ceelo

import android.content.Intent
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import game.ceelo.GameResult.*
import game.ceelo.Hand.getDiceImageFromDiceValue
import game.ceelo.R.drawable.*
import game.ceelo.databinding.ActivityGameBinding

fun ActivityGameBinding.loadLocalGame(
    gameActivity: GameActivity,
    gameViewModel: GameViewModel,
    playersUI: List<List<ImageView>>,
    resultUI: List<TextView>
) = apply {
    gameViewModel.diceGame.observe(gameActivity) { game ->
        playersUI.mapIndexed { i, it ->
            playerUI(game[i], diceImages, it)
        }
    }

    resultUI.mapIndexed { i, view ->
        gameViewModel
            .resultPairList
            .observe(gameActivity) { result ->
                setTextViewResult(view, result[i].first, result[i].second)
            }
    }

    playLocalButton.setOnClickListener {
        gameViewModel.apply {
            onClickPlayButton()
            resultUI.mapIndexed { i, it ->
                playerThrow(
                    playersUI[i],
                    diceGame.value!![i],
                    this,
                    it,
                    when (i) {
                        0 -> resultPairList.value?.first()?.first
                        else -> resultPairList.value?.get(1)?.first
                    }!!
                )
            }
        }
    }

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
}

val diceImages
    get() = listOf(
        dice_face_one,
        dice_face_two,
        dice_face_three,
        dice_face_four,
        dice_face_five,
        dice_face_six,
    )

fun playerThrow(
    playerUI: List<ImageView>,
    list: List<Int>,
    gameViewModel: GameViewModel,
    resultUI: TextView,
    playerResult: GameResult
) = playerUI.mapIndexed { i, view ->
    runDiceAnimation(view, list[i])
}.run {
    setTextViewResult(
        resultUI,
        playerResult,
        gameViewModel.resultVisibility.value!!
    )
}


fun playerUI(
    game: List<Int>,
    diceImages: List<Int>,
    images: List<ImageView>
) = images.mapIndexed { i, image ->
    image.setImageResource(diceImages.getDiceImageFromDiceValue(game[i]))
}


fun runDiceAnimation(
    diceImage: ImageView,
    diceValue: Int,
) = diceImage.apply {
    setImageResource(diceImages.getDiceImageFromDiceValue(diceValue))
}.run {
    startAnimation(RotateAnimation(
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
