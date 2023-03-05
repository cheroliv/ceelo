package game.ceelo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import game.ceelo.controller.binding
import game.ceelo.vm.GameViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel<GameViewModel>().loadLocalGame(this, binding)
    }
}