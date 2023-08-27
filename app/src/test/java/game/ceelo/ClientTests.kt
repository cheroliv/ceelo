@file:Suppress("unused")

package game.ceelo

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.HttpHeaders.ContentType
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class ClientTests {
    @Serializable
    data class IpResponse(val ip: String)

    class ApiClient(
        @Suppress("MemberVisibilityCanBePrivate")
        val engine: HttpClientEngine,
        private val httpClient: HttpClient = HttpClient(engine) {
            install(ContentNegotiation) { json() }
        }
    ) {
        suspend fun getIp(): IpResponse = httpClient
            .get("https://api.ipify.org/?format=json")
            .body()
    }

    @Test
    fun testMockServer() = runBlocking {
        assertEquals("127.0.0.1", ApiClient(engine = MockEngine {
            respond(
                content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                status = OK,
                headers = headersOf(ContentType, "application/problem+json")
            )
        }).getIp().ip)
    }
}