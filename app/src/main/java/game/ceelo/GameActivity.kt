package game.ceelo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import game.ceelo.databinding.ActivityGameBinding.inflate
import org.koin.androidx.viewmodel.ext.android.getViewModel


class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate(layoutInflater).apply {
            setContentView(root)
            loadLocalGame(
                this@GameActivity,
                getViewModel(),
                playersUI = listOf(
                    listOf(
                        playerOneFirstDiceImageId,
                        playerOneMiddleDiceImageId,
                        playerOneLastDiceImageId
                    ),
                    listOf(
                        playerTwoFirstDiceImageId,
                        playerTwoMiddleDiceImageId,
                        playerTwoLastDiceImageId
                    )
                ),
                resultUI = listOf(
                    localPlayerResultText,
                    computerResultText
                )
            )
        }
    }
}