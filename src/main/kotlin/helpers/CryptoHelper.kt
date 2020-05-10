package helpers

import java.security.MessageDigest

fun sha512(text: String): String{
    val bytes = text.toByteArray()
    val md = MessageDigest.getInstance("SHA-512")
    val digest = md.digest(bytes)
    return digest.fold("", {str, it -> str + "%02x".format(it)})
}