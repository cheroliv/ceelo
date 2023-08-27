package game.ceelo.auth

interface AuthentificationService {
    fun login(username: String, password: String): Boolean
}