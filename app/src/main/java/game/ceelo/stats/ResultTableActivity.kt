@file:Suppress("MemberVisibilityCanBePrivate")

package game.ceelo.stats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import game.ceelo.CeeloService
import game.ceelo.databinding.ActivityResultTableBinding.inflate
import org.koin.android.ext.android.get

class ResultTableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate(layoutInflater).apply {
            setContentView(root)
            backButton.apply { setOnClickListener { finish() } }
            resultTable.apply {
                adapter = ResultsAdapter(get<CeeloService>().allGames())
                layoutManager = LinearLayoutManager(this@ResultTableActivity)
            }
        }
    }

}

