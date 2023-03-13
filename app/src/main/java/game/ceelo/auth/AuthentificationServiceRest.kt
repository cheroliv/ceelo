package game.ceelo.auth

class AuthentificationServiceRest : AuthentificationService {
    override fun login(username: String, password: String): Boolean {
        return true
    }
}