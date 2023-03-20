//package webapp.models

@file:Suppress("unused")

package webapp.models

//import jakarta.validation.constraints.*
import webapp.*
import java.time.Instant
import java.util.*

/**
 * Représente l'account domain model avec le password et l'activationKey
 * pour la vue
 */
data class AccountCredentials(
    val id: UUID? = null,
//    @field:Size(min = PASSWORD_MIN, max = PASSWORD_MAX)
//    @field:NotNull
    val password: String? = null,
//    @field:NotBlank
//    @field:Pattern(regexp = LOGIN_REGEX)
//    @field:Size(min = 1, max = 50)
    val login: String? = null,
//    @field:Email
//    @field:Size(min = 5, max = 254)
    val email: String? = null,
//    @field:Size(max = 50)
    val firstName: String? = null,
//    @field:Size(max = 50)
    val lastName: String? = null,
//    @field:Size(min = 2, max = 10)
    val langKey: String? = null,
//    @field:Size(max = 256)
    val imageUrl: String? = null,
    val activationKey: String? = null,
    val resetKey: String? = null,
    val activated: Boolean = false,
    val createdBy: String? = null,
    val createdDate: Instant? = null,
    val lastModifiedBy: String? = null,
    val lastModifiedDate: Instant? = null,
    val authorities: Set<String>? = null
) {

    constructor(account: Account) : this(
        id = account.id,
        login = account.login,
        email = account.email,
        firstName = account.firstName,
        lastName = account.lastName,
        langKey = account.langKey,
        activated = account.activated,
        createdBy = account.createdBy,
        createdDate = account.createdDate,
        lastModifiedBy = account.lastModifiedBy,
        lastModifiedDate = account.lastModifiedDate,
        imageUrl = account.imageUrl,
        authorities = account.authorities?.map { it }?.toMutableSet()
    )

    companion object {
        val String.objectName get() = replaceFirst(first(), first().lowercaseChar())

        @JvmStatic
        val objectName = AccountCredentials::class.java.simpleName.objectName
    }

    val toAccount: Account
        get() = Account(
            id = id,
            login = login,
            firstName = firstName,
            lastName = lastName,
            email = email,
            activated = activated,
            langKey = langKey,
            createdBy = createdBy,
            createdDate = createdDate,
            lastModifiedBy = lastModifiedBy,
            lastModifiedDate = lastModifiedDate,
            authorities = authorities
        )
}




