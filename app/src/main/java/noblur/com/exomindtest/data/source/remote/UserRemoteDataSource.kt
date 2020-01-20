package noblur.com.exomindtest.data.source.remote

import android.util.Log
import androidx.annotation.VisibleForTesting
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.repository.UserDataSource


class UserRemoteDataSource(
    val compositeDisposable: CompositeDisposable,
    val api: ExomindService
) : UserDataSource {


    override fun getUsers(callback: UserDataSource.GetUsersCallback) {


        compositeDisposable?.add(api.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {it->

                    callback.onUsersLoaded(it)},
                { error ->

                    callback.onDataNotAvailable(500)
                }
            )
        )
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