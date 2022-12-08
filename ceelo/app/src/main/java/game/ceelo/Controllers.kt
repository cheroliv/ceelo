@file:Suppress("UNUSED_VARIABLE")

package game.ceelo

import android.content.Intent
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import game.ceelo.CeeloDicesHandDomain.getDiceImageFromDiceValue
import game.ceelo.DiceRunResult.*
import game.ceelo.databinding.ActivityMainBinding

fun ActivityMainBinding.loadLocalGame(
    mainActivity: MainActivity,
    diceGameViewModel: DiceGameViewModel,
    playersUI: List<List<ImageView>>,
    resultUI: List<TextView>,
): ActivityMainBinding = apply {

    diceGameViewModel.diceGame.observe(mainActivity) { game ->
        playersUI.mapIndexed { i, it ->
            playerUI(game[i], diceImages, it)
        }
    }

    resultUI.mapIndexed { i, it ->
        diceGameViewModel
            .resultPairList
            .observe(mainActivity) { result ->
                setTextViewResult(it, result[i].first, result[i].second)
            }
    }

    playLocalButton.setOnClickListener {
        diceGameViewModel.apply {
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

    statsButton.setOnClickListener {
        mainActivity.startActivity(Intent(mainActivity, StatsActivity::class.java))
    }

    signinButton.setOnClickListener {
        mainActivity.startActivity(Intent(mainActivity, LoginActivity::class.java))
    }
}

val diceImages: List<Int>
    get() = listOf(
        R.drawable.dice_face_one,
        R.drawable.dice_face_two,
        R.drawable.dice_face_three,
        R.drawable.dice_face_four,
        R.drawable.dice_face_five,
        R.drawable.dice_face_six,
    )

fun playerThrow(
    playerUI: List<ImageView>,
    list: List<Int>,
    diceGameViewModel: DiceGameViewModel,
    resultUI: TextView,
    playerResult: DiceRunResult
) = playerUI.mapIndexed { i, view ->
    runDiceAnimation(view, list[i])
}.run {
    setTextViewResult(
        resultUI,
        playerResult,
        diceGameViewModel.resultVisibility.value!!
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
): Unit = diceImage.apply {
    setImageResource(diceImages.getDiceImageFromDiceValue(diceValue = diceValue))
}.run {
    startAnimation(RotateAnimation(
        0f,
        360f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    ).apply { duration = 500 })
}

fun setTextViewResult(
    textViewResult: TextView,
    diceResult: DiceRunResult,
    textViewVisibility: Int
): TextView = textViewResult.apply {
    visibility = textViewVisibility
    text = when (diceResult) {
        WIN -> WIN.toString()
        LOOSE -> LOOSE.toString()
        else -> RERUN.toString()
    }
}
