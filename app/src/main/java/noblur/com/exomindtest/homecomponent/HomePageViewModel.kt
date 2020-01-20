package noblur.com.exomindtest.homecomponent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.repository.UserDataSource
import noblur.com.exomindtest.data.repository.UserRepository

class HomePageViewModel(
    val userRepository: UserRepository
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>().apply { value = emptyList() }
    val users: LiveData<List<User>>
        get() = _users


    fun start() {

        loadUser()
    }

    private fun loadUser() {

        userRepository.getUsers(object :UserDataSource.GetUsersCallback{
            override fun onUsersLoaded(users: List<User>) {

                _users.postValue(users)

            }

            override fun onDataNotAvailable(code: Int) {


            }

        })
    }


}
