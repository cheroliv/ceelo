package webapp.models

data class ProblemsModel(
    val type: String,
    val title: String,
    val status: Int,
    val path: String = "",
    val message: String,
    val fieldErrors: MutableSet<Map<String, String>> = mutableSetOf()
)