package game.ceelo.entities

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

object EntityUtils {
    val ZonedDateTime.fromDateTime get() = toInstant()?.toEpochMilli()

    val Long.toDateTime: ZonedDateTime?
        get() = Instant.ofEpochMilli(this)
            .atZone(ZoneId.systemDefault())
}