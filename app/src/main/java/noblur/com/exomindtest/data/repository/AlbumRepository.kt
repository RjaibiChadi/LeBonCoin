package noblur.com.exomindtest.data.repository

import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.User


class AlbumRepository(
    val albumRemoteDataSource: AlbumDataSource,
    val albumLocalDataSource: AlbumDataSource
): AlbumDataSource {

    override fun getAlbumsByUserId(userId: Int, callback: AlbumDataSource.GetAlbumsCallback) {


    }

    override fun registerAlbums(albums: List<Album>) {

    }

    override fun deleteAllAlbums() {

    }



    companion object {

        private var INSTANCE: AlbumRepository? = null


        @JvmStatic fun getInstance(albumRemoteDataSource: AlbumDataSource,
                                   albumLocalDataSource: AlbumDataSource
        ) =
            INSTANCE
                ?: synchronized(AlbumRepository::class.java) {
                INSTANCE
                    ?: AlbumRepository(
                        albumRemoteDataSource,
                        albumLocalDataSource
                    )
                    .also { INSTANCE = it }
            }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }



}