package store

import dao.UsersDao
import models.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserStore(private val userDao: UsersDao) {


    fun insertUser(newUser: User){
        transaction {
            userDao.insert {
                it[displayName] = newUser.displayName!!
                it[email] = newUser.email!!
                it[password] = newUser.password!!
            }
        }
    }

    fun userAlreadyExist(email: String) : Boolean{
        return transaction {
            userDao.select{ userDao.email eq email }.any()
        }
    }

}