package game.ceelo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import game.ceelo.databinding.ActivityMainBinding.inflate
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val diceGameViewModel: DiceGameViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate(layoutInflater).apply {
            setContentView(root)
            loadLocalGame(
                mainActivity = this@MainActivity,
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
                    ),
                ),
                resultUI = listOf(
                    localPlayerResultText,
                    computerResultText
                ),
                diceGameViewModel = diceGameViewModel
            )
        }
    }
}