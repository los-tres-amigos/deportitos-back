import controllers.AuthController
import dao.UsersDao
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import services.HelloRepository
import services.HelloService
import services.HelloServiceImpl
import store.UserStore

val helloAppModule = module {
    single<HelloService> { HelloServiceImpl(get()) }
    single { HelloRepository() }
    single { AuthController(get()) }
    single { UserStore(get()) }
    single { UsersDao }
}

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, 8080) {
        install(ContentNegotiation){
            jackson { }
        }
        install(Koin) {
            modules(helloAppModule)
        }

        Database.connect("jdbc:postgresql://localhost:5432/deportitosdb", driver = "org.postgresql.Driver",
                user = "postgres", password = "pass123") // TODO: Change this way to use the "user-secret" way o something similar.
        transaction { SchemaUtils.create(UsersDao) }

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
