package noblur.com.leboncoin.data.repository

import noblur.com.leboncoin.data.entities.Album


interface AlbumDataSource {



    interface GetAlbumsCallback {

        fun onAlbumsLoaded(albums: List<Album>)

        fun onDataNotAvailable(code:Int)
    }


    fun getAlbums(callback: GetAlbumsCallback)

    fun registerAlbums(albums: List<Album>)

    fun deleteAllAlbums()

}