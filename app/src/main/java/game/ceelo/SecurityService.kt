package game.ceelo

class SecurityService : ISecurityService {
    override fun login(username: String, password: String): Boolean {
        return true
    }
}