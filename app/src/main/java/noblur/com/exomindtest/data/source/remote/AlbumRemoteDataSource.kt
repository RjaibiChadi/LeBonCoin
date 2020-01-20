package noblur.com.exomindtest.data.source.remote

import androidx.annotation.VisibleForTesting
import io.reactivex.disposables.CompositeDisposable
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.repository.AlbumDataSource
import noblur.com.exomindtest.data.repository.UserDataSource


class AlbumRemoteDataSource(
    val compositeDisposable: CompositeDisposable,
    val api: ExomindService
) : AlbumDataSource {

    override fun getAlbumsByUserId(userId: Int, callback: AlbumDataSource.GetAlbumsCallback) {

    }

    override fun registerAlbums(albums: List<Album>) {

    }

    override fun deleteAllAlbums() {

    }


    companion object {
        private var INSTANCE: AlbumRemoteDataSource? = null

        @JvmStatic
        fun getInstance(
            compositeDisposable: CompositeDisposable,
            api: ExomindService
        ): AlbumRemoteDataSource {
            if (INSTANCE == null) {
                synchronized(AlbumRemoteDataSource::javaClass) {
                    INSTANCE = AlbumRemoteDataSource(compositeDisposable,api)
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