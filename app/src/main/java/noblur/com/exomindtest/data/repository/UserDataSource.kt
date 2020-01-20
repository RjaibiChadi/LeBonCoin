package noblur.com.exomindtest.data.repository

import noblur.com.exomindtest.data.entities.User


interface UserDataSource {



    interface GetUsersCallback {

        fun onUsersLoaded(users: List<User>)

        fun onDataNotAvailable(code:Int)
    }

    interface GetUserCallback {

        fun onUserLoaded(user: User)

        fun onDataNotAvailable(code:Int)
    }

    fun getUsers(callback: GetUsersCallback)

    fun getUserByName(query:String,callback: GetUserCallback)

    fun registerUser(users: List<User>)

    fun deleteAllUser()

}