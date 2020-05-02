import controllers.AuthController
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import services.HelloRepository
import services.HelloService
import services.HelloServiceImpl

val helloAppModule = module {
    single<HelloService> { HelloServiceImpl(get()) }
    single { HelloRepository() }
    single { AuthController() }
}

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, 8080) {
        install(ContentNegotiation){
            jackson { }
        }
        install(Koin) {
            modules(helloAppModule)
        }

        val authController by inject<AuthController>()

        routing {
            // AUTH METHODS
            route("/auth"){
                post("register"){ authController.register(call) }
            }
        }
    }
    server.start(true)
}
