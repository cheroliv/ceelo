package webapp.models

data class Signup(
    val login:String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val imageUrl: String,
    val langKey: String,
)