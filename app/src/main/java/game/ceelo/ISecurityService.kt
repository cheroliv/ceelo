package game.ceelo

interface ISecurityService {
    fun login(username: String, password: String): Boolean
}