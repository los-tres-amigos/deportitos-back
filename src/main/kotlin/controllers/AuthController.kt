package controllers

import helpers.sha512
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond
import models.User
import store.UserStore
import wrappers.Responder

class AuthController (private val userStore: UserStore){

    suspend fun register(call: ApplicationCall) {
        val user = call.receive<User>()

        // Checking the user has the mandatory fields
        if (user.displayName.isNullOrEmpty() || user.email.isNullOrEmpty() || user.password.isNullOrEmpty()){
            val response = Responder(false, "All the fields are mandatory.", null)
            call.respond(response)
            return
        }

        // Checking the user does not exist already
        if (userStore.userAlreadyExist(user.email!!)){
           val response = Responder(false, "The user already exist.", null)
            call.respond(response)
            return
        }

        // Encrypting the password and creating the new user
        user.password = sha512(user.password!!)
        userStore.insertUser(user)

        // TODO: Change this to redirect to login method which will creates the token by a obtained user.
        // Returning the user
        val response = Responder(true, "User has been created.", user)
        call.respond(response)
    }

}
