package noblur.com.exomindtest.data.source.local.albumlocal


import androidx.annotation.VisibleForTesting
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.repository.AlbumDataSource
import noblur.com.exomindtest.data.repository.UserDataSource
import noblur.com.exomindtest.utils.AppExecutors


class AlbumLocalDataSource(
    val appExecutors: AppExecutors,
    val albumDao: AlbumDao
): AlbumDataSource {

    override fun getAlbumsByUserId(userId: Int, callback: AlbumDataSource.GetAlbumsCallback) {

    }

    override fun registerAlbums(albums: List<Album>) {

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