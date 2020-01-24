package noblur.com.leboncoin.data.repository

import noblur.com.leboncoin.data.entities.Album


class AlbumRepository(
    val albumRemoteDataSource: AlbumDataSource,
    val albumLocalDataSource: AlbumDataSource
): AlbumDataSource {


    override fun getAlbums(callback: AlbumDataSource.GetAlbumsCallback) {
        albumLocalDataSource.getAlbums(object :AlbumDataSource.GetAlbumsCallback{
            override fun onAlbumsLoaded(albums: List<Album>) {

                callback.onAlbumsLoaded(albums)
            }

            override fun onDataNotAvailable(code: Int) {

                getAlbumsByIdRemote(callback)
            }

        })

    }


    private fun getAlbumsByIdRemote(callback: AlbumDataSource.GetAlbumsCallback) {

        albumRemoteDataSource.getAlbums(object :AlbumDataSource.GetAlbumsCallback{
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