package noblur.com.leboncoin.data.source.local.albumlocal


import android.util.Log
import androidx.annotation.VisibleForTesting
import noblur.com.leboncoin.data.entities.Album
import noblur.com.leboncoin.data.repository.AlbumDataSource
import noblur.com.leboncoin.utils.AppExecutors


class AlbumLocalDataSource(
    val appExecutors: AppExecutors,
    val albumDao: AlbumDao
): AlbumDataSource {
    override fun getAlbums(callback: AlbumDataSource.GetAlbumsCallback) {

        appExecutors.diskIO.execute {

            val albums = albumDao.getAlbums()
            appExecutors.diskIO.execute {
                if (albums.isEmpty()){

                    callback.onDataNotAvailable(500)
                }else{
                    callback.onAlbumsLoaded(albums)

                }

            }
        }
    }


    override fun registerAlbums(albums: List<Album>) {
        appExecutors.diskIO.execute { albumDao.insertAlbum(albums) }

    }

    override fun deleteAllAlbums() {

    }


    companion object {
  private var INSTANCE: AlbumLocalDataSource? = null

       @JvmStatic
       fun getInstance(appExecutors: AppExecutors, albumDao: AlbumDao): AlbumLocalDataSource {
        if (INSTANCE == null) {
         synchronized(AlbumLocalDataSource::javaClass) {
          INSTANCE =
              AlbumLocalDataSource(appExecutors, albumDao)
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