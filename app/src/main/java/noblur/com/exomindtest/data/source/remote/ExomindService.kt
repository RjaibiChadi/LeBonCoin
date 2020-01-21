package noblur.com.exomindtest.data.source.remote

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.reactivex.Observable
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.Photo
import noblur.com.exomindtest.data.entities.User
import retrofit2.http.*

interface ExomindService {

    @GET("users")
    fun getUsers() : Observable<List<User>>

    @GET("users/1/albums?")
    fun getAlbums(@Query("userId") userId: Int) : Observable<List<Album>>

    @GET("users/1/photos?")
    fun getPhotos(@Query("albumId") albumId: Int) : Observable<List<Photo>>
}