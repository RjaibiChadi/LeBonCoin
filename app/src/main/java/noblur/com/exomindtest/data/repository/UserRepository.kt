package noblur.com.exomindtest.data.repository

import android.util.Log
import noblur.com.exomindtest.data.entities.User


class UserRepository(
    val userRemoteDataSource: UserDataSource,
    val userLocalDataSource: UserDataSource
): UserDataSource {

    override fun getUsers(callback: UserDataSource.GetUsersCallback) {

        userLocalDataSource.getUsers(object :UserDataSource.GetUsersCallback{
            override fun onUsersLoaded(users: List<User>) {

                callback.onUsersLoaded(users)

            }

            override fun onDataNotAvailable(code: Int) {

                loadUserRemote(callback)

            }

        })

    }

    private fun loadUserRemote(callback: UserDataSource.GetUsersCallback) {

        userRemoteDataSource.getUsers(object :UserDataSource.GetUsersCallback{
            override fun onUsersLoaded(users: List<User>) {
                callback.onUsersLoaded(users)
                userLocalDataSource.registerUser(users)
            }

            override fun onDataNotAvailable(code: Int) {
                callback.onDataNotAvailable(code)

            }

        })
    }

    override fun getUserByName(query: String, callback: UserDataSource.GetUserCallback) {

    }

    override fun registerUser(users: List<User>) {

    }

    override fun deleteAllUser() {

    }


    companion object {

        private var INSTANCE: UserRepository? = null


        @JvmStatic fun getInstance(userRemoteDataSource: UserDataSource,
                                   userLocalDataSource: UserDataSource
        ) =
            INSTANCE
                ?: synchronized(UserRepository::class.java) {
                INSTANCE
                    ?: UserRepository(
                        userRemoteDataSource,
                        userLocalDataSource
                    )
                    .also { INSTANCE = it }
            }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }


}