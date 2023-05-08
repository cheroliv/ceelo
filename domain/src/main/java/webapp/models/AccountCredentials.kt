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
    val id: Long? = null,
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
//register
// Code	Description
//201 Created
/*
{
  "id": 0,
  "login": "string",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "imageUrl": "string",
  "activated": true,
  "langKey": "string",
  "createdBy": "string",
  "createdDate": "2023-05-07T15:52:49.651Z",
  "lastModifiedBy": "string",
  "lastModifiedDate": "2023-05-07T15:52:49.651Z",
  "authorities": [
    "string"
  ],
  "password": "string"
}
 */
/*
ManagedUserVM{
    id	integer($int64)
    login*	string
    maxLength: 50
    minLength: 1
    pattern: ^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$
    firstName	string
    maxLength: 50
    minLength: 0
    lastName	string
    maxLength: 50
    minLength: 0
    email	string
    maxLength: 254
    minLength: 5
    imageUrl	string
    maxLength: 256
    minLength: 0
    activated	boolean
    langKey	string
    maxLength: 10
    minLength: 2
    createdBy	string
    createdDate	string($date-time)
    lastModifiedBy	string
    lastModifiedDate	string($date-time)
    authorities	        [ uniqueItems: true
                            string]
    password	string
    maxLength: 100
    minLength: 4
}
*/




