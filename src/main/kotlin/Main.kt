import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import models.User
import org.jetbrains.exposed.sql.Database
import wrappers.Responder

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, 8080) {
        install(ContentNegotiation){
            jackson { }
        }
        Database.connect("jdbc:postgresql://localhost:5432/Deportitos", driver = "org.postgresql.Driver",
                user = "postgres", password = "pass123") // TODO: Change this way to use the "secret" way.

        routing {
            get("/") {
                call.respondText("Hello World!", ContentType.Text.Plain)
            }

            // AUTH METHODS
            route("/auth"){
                post("register"){

                    val user = call.receive<User>()

                    // Checking the user has the mandatory fields
                    if (user.displayName.isNullOrEmpty() || user.email.isNullOrEmpty() || user.password.isNullOrEmpty()){
                        val response = Responder(false, "All the fields are mandatory.", null)
                        call.respond(response)
                    }

                    // Checking the user does not exist already


                    // Creating the new user

                    // Creating the token

                    // Returning the token
                    val response = Responder(true, "User has been created.", user)
                    call.respond(response)
                }
            }
        }
    }
    server.start(true)
}
