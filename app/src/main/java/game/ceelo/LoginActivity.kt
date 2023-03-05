@file:Suppress("UNUSED_VARIABLE", "unused")

package game.ceelo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import game.ceelo.databinding.ActivityLoginBinding.inflate
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate(layoutInflater).apply {
            setContentView(root)
            login.setOnClickListener {
                val authService = null
                if (inject<ISecurityService>().value.login(
                        username.text.toString(),
                        password.text.toString()
                    )) finish()
            }
        }
    }
}