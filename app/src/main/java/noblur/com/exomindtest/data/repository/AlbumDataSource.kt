package noblur.com.exomindtest.data.repository

import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.User


interface AlbumDataSource {



    interface GetAlbumsCallback {

        fun onAlbumsLoaded(albums: List<Album>)

        fun onDataNotAvailable(code:Int)
    }


    fun getAlbumsByUserId(userId:Int,callback: GetAlbumsCallback)

    fun registerAlbums(albums: List<Album>)

    fun deleteAllAlbums()

}