package game.ceelo.auth

class AuthentificationServiceKtor : AuthentificationService {
    override fun login(username: String, password: String): Boolean {
        return true
    }
}