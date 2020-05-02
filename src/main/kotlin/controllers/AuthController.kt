package controllers

import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond
import models.User
import wrappers.Responder

class AuthController {

    suspend fun register(call: ApplicationCall) {
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
