package dao

import org.jetbrains.exposed.sql.Table

object UsersDao : Table("User"){

    var idUser = integer("IdUser").autoIncrement()
    var displayName = varchar("DisplayName", 150)
    var email = varchar("Email", 250)
    var password = varchar("Password", 128)
}