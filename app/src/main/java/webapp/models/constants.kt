@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package webapp.models

const val IMAGE_URL_DEFAULT = "http://placehold.it/50x50"
// Regex for acceptable logins
const val LOGIN_REGEX =
    "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$"
const val PASSWORD_MIN: Int = 4
const val PASSWORD_MAX: Int = 24