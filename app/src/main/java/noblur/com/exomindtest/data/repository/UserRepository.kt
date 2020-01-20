package noblur.com.exomindtest.data.repository

import noblur.com.exomindtest.data.entities.User


class UserRepository(
    val userRemoteDataSource: UserDataSource,
    val userLocalDataSource: UserDataSource
): UserDataSource {

    override fun getUsers(callback: UserDataSource.GetUsersCallback) {

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