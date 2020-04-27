package models

class User {

    private var idUser: Int
    private var displayName: String
    private var password: String
    private lateinit var email: String
    private lateinit var blockUserList: List<User>

    constructor(idUser: Int, displayName: String, password: String, email: String, blockUserList: List<User>){
        this.idUser = idUser
        this.displayName = displayName
        this.password = password
        this.email = email
        this.blockUserList = blockUserList
    }

    constructor(displayName: String, password: String){
        this.displayName = displayName
        this.password = password
        this.idUser = 0 // Esto es lo mas feo que he visto en toda mi vida. TODO Mirar a ver que coño le pasa.
    }


    // Setters
    fun setDisplayName(newDisplayName: String){
        // TODO: add regex validation(?)

        if(newDisplayName.isNotEmpty())
            this.displayName = newDisplayName;
    }

    fun setPassword(newPassword: String) {
        var passIsOk = true

        if(newPassword.isEmpty())
            passIsOk = false

        if(newPassword.length < 8)
            passIsOk = false

        // TODO: Add regex

        if(passIsOk)
            this.password = newPassword
    }

    fun setEmail(newEmail: String){
        // TODO: add regex

        if(newEmail.isNotEmpty()){
            this.email = newEmail
        }
    }

    fun setBlockedUserList(blockUserList: List<User>){
        this.blockUserList = blockUserList
    }

    // Getters
    fun getIdUser(): Int{
        return this.idUser
    }

    fun getDisplayName(): String{
        return this.displayName
    }

    fun getEmail(): String{
        return this.email
    }

    fun getPassword(): String{
        return this.password
    }

    fun getBlockUserList(): List<User>{
        return this.blockUserList
    }
}