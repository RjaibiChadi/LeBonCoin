package noblur.com.exomindtest.data.repository

import android.util.Log
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.User


class AlbumRepository(
    val albumRemoteDataSource: AlbumDataSource,
    val albumLocalDataSource: AlbumDataSource
): AlbumDataSource {

    override fun getAlbumsByUserId(userId: Int, callback: AlbumDataSource.GetAlbumsCallback) {

        albumLocalDataSource.getAlbumsByUserId(userId,object :AlbumDataSource.GetAlbumsCallback{
            override fun onAlbumsLoaded(albums: List<Album>) {

                callback.onAlbumsLoaded(albums)
            }

            override fun onDataNotAvailable(code: Int) {

                getAlbumsByIdRemote(userId,callback)
            }

        })

    }

    private fun getAlbumsByIdRemote(userId: Int,callback: AlbumDataSource.GetAlbumsCallback) {

        albumRemoteDataSource.getAlbumsByUserId(userId,object :AlbumDataSource.GetAlbumsCallback{
            override fun onAlbumsLoaded(albums: List<Album>) {

                callback.onAlbumsLoaded(albums)
                albumLocalDataSource.registerAlbums(albums)

            }

            override fun onDataNotAvailable(code: Int) {

                callback.onDataNotAvailable(code)

            }


        })
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