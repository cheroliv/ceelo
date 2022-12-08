package game.ceelo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import game.ceelo.databinding.ActivityMainBinding.inflate
import org.koin.android.ext.android.get
import org.koin.core.context.KoinContext


class MainActivity : AppCompatActivity() {

//    val gameViewModel: GameViewModel by org.koin.androidx.viewmodel.ext.android.viewModel()
//lateinit var gameViewModel:GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate(layoutInflater).apply {
            setContentView(root)
            loadLocalGame(
                mainActivity = this@MainActivity,
                ViewModelProvider(this@MainActivity)[DiceGameViewModel::class.java],
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