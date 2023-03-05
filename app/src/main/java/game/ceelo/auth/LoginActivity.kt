@file:Suppress("UNUSED_VARIABLE", "unused")

package game.ceelo.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import game.ceelo.databinding.ActivityLoginBinding.inflate
import org.koin.android.ext.android.get

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate(layoutInflater).apply {
            setContentView(root)
            login.setOnClickListener {
                val authService = null
                if (get<ISecurityService>().login(
                        username.text.toString(),
                        password.text.toString()
                    )) finish()
            }
        }
    }
}