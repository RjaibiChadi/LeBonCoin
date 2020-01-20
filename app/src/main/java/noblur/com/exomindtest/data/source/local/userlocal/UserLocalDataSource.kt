package noblur.com.exomindtest.data.source.local.userlocal


import androidx.annotation.VisibleForTesting
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.repository.UserDataSource
import noblur.com.exomindtest.utils.AppExecutors


class UserLocalDataSource(
    val appExecutors: AppExecutors,
    val userDao: UserDao
): UserDataSource {

    override fun getUsers(callback: UserDataSource.GetUsersCallback) {

        appExecutors.diskIO.execute {

            val users = userDao.getUsers()
            appExecutors.diskIO.execute {
                if (users.isEmpty()){

                    callback.onDataNotAvailable(500)
                }else{
                    callback.onUsersLoaded(users)

                }

            }
        }

    }

    override fun getUserByName(query: String, callback: UserDataSource.GetUserCallback) {

    }

    override fun registerUser(users: List<User>) {
        appExecutors.diskIO.execute { userDao.insertUser(users) }

    }

    override fun deleteAllUser() {

    }


    companion object {
  private var INSTANCE: UserLocalDataSource? = null

       @JvmStatic
       fun getInstance(appExecutors: AppExecutors, userDao: UserDao): UserLocalDataSource {
        if (INSTANCE == null) {
         synchronized(UserLocalDataSource::javaClass) {
          INSTANCE =
              UserLocalDataSource(appExecutors, userDao)
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