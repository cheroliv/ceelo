package game.ceelo.auth

interface ISecurityService {
    fun login(username: String, password: String): Boolean
}