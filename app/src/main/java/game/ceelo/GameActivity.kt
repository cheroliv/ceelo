package game.ceelo

import android.os.Bundle
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import game.ceelo.GameResult.*
import game.ceelo.Hand.getDiceImageFromDiceValue
import game.ceelo.R.drawable.*
import game.ceelo.databinding.ActivityGameBinding
import game.ceelo.databinding.ActivityGameBinding.inflate
import org.koin.androidx.viewmodel.ext.android.getViewModel

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel<GameViewModel>().loadLocalGame(this)
    }
}
val GameActivity.binding: ActivityGameBinding
    get() = inflate(layoutInflater)
        .apply { setContentView(root) }
val ActivityGameBinding.playerOneUI
    get() = listOf(
        playerOneFirstDice,
        playerOneMiddleDice,
        playerOneLastDice
    )
val ActivityGameBinding.playerTwoUI
    get() = listOf(
        playerTwoFirstDice,
        playerTwoMiddleDice,
        playerTwoLastDice
    )

val ActivityGameBinding.playersUI: List<List<ImageView>>
    get() = listOf(playerOneUI, playerTwoUI)



val ActivityGameBinding.resultUI: List<TextView>
    get() = listOf(localPlayerResult, computerResult)

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
): TextView = textViewResult.apply {
    visibility = textViewVisibility
    text = when (diceResult) {
        WIN -> WIN.toString()
        LOOSE -> LOOSE.toString()
        else -> RERUN.toString()
    }
}



