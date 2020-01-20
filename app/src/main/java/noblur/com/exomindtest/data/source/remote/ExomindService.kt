package noblur.com.exomindtest.data.source.remote

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.reactivex.Observable
import noblur.com.exomindtest.data.entities.User
import retrofit2.http.*

interface ExomindService {

    @GET("users")
    fun getUsers() : Observable<User>


}