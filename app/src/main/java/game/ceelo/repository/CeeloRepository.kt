package game.ceelo.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import game.ceelo.repository.entity.DicesThrow
import game.ceelo.repository.entity.Game

@Dao
interface CeeloRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDice(dice: DicesThrow?)

    @Query("SELECT * FROM dice ")
    fun getAllDices(): LiveData<List<DicesThrow>>?

    @Query(value = "SELECT count(*) FROM dice")
    fun countDices(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: Game?)

    @Query("SELECT * FROM game ")
    fun getAllGames(): LiveData<List<Game>>?

    @Query(value = "SELECT count(*) FROM game ")
    fun countGames(): Int

}