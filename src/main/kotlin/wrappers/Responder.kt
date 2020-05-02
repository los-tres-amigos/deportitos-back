package wrappers

class Responder <T: Any?> (var success: Boolean, var message: String, var response: T? = null) {
}