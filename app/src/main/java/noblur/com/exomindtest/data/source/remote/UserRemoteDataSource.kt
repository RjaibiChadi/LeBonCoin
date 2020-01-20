package noblur.com.exomindtest.data.source.remote

import androidx.annotation.VisibleForTesting
import io.reactivex.disposables.CompositeDisposable
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.repository.UserDataSource


class UserRemoteDataSource(
    val compositeDisposable: CompositeDisposable,
    val api: ExomindService
) : UserDataSource {


    override fun getUsers(callback: UserDataSource.GetUsersCallback) {

    }

    override fun getUserByName(query: String, callback: UserDataSource.GetUserCallback) {

    }

    override fun registerUser(users: List<User>) {

    }

    override fun deleteAllUser() {

    }

    companion object {
        private var INSTANCE: UserRemoteDataSource? = null

        @JvmStatic
        fun getInstance(
            compositeDisposable: CompositeDisposable,
            api: ExomindService
        ): UserRemoteDataSource {
            if (INSTANCE == null) {
                synchronized(UserRemoteDataSource::javaClass) {
                    INSTANCE = UserRemoteDataSource(compositeDisposable,api)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }


}