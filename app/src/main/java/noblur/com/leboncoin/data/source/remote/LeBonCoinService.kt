package noblur.com.leboncoin.data.source.remote

import io.reactivex.Observable
import noblur.com.leboncoin.data.entities.Album
import retrofit2.http.*

interface LeBonCoinService {

    @GET("technical-test.json")
    fun getAlbums() : Observable<List<Album>>


}