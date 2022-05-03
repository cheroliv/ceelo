package game.ceelo.service

import android.content.Context
import androidx.lifecycle.LiveData
import game.ceelo.database.CeeloDataBase
import game.ceelo.repository.entity.DicesThrow
import game.ceelo.repository.CeeloRepository
import game.ceelo.repository.entity.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CeeloService  private constructor(context: Context) {
    private val database: CeeloDataBase? = CeeloDataBase.getInstance(context)

    fun getAllDices() : LiveData<List<DicesThrow>>? {
        val dicesThrowRepository: CeeloRepository? = database?.dicesThrowRepository()
        return dicesThrowRepository?.getAllDices()
    }

    fun insertDice(dice : DicesThrow) {
        CoroutineScope(Dispatchers.IO).launch {
            database?.dicesThrowRepository()?.insertDice(dice)
        }
    }

    fun getAllGames() : LiveData<List<Game>>? {
        val dicesThrowRepository: CeeloRepository? = database?.dicesThrowRepository()
        return dicesThrowRepository?.getAllGames()
    }

    fun insertGame(game: Game) {
        CoroutineScope(Dispatchers.IO).launch {
            database?.dicesThrowRepository()?.insertGame(game)
        }
    }

    companion object {
        private var instance: CeeloService? = null
        fun getInstance(context: Context): CeeloService? {
            if (instance == null) {
                instance = CeeloService(context)
            }
            return instance
        }
    }
}