package game.ceelo.entities

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

object EntitiesUtils {
    val ZonedDateTime.fZDT get() = toInstant()?.toEpochMilli()

    val Long.tZDT: ZonedDateTime?
        get() = Instant.ofEpochMilli(this)
            .atZone(ZoneId.systemDefault())
}