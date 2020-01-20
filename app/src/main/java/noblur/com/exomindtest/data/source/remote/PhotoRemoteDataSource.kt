package noblur.com.exomindtest.data.source.remote

import androidx.annotation.VisibleForTesting
import io.reactivex.disposables.CompositeDisposable
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.Photo
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.repository.AlbumDataSource
import noblur.com.exomindtest.data.repository.PhotoDataSource
import noblur.com.exomindtest.data.repository.UserDataSource


class PhotoRemoteDataSource(
    val compositeDisposable: CompositeDisposable,
    val api: ExomindService
) : PhotoDataSource {

    override fun getPhotosByAlbumId(albumId: Int, callback: PhotoDataSource.GetPhotosCallback) {

    }

    override fun registerPhotos(photos: List<Photo>) {

    }

    override fun deleteAllPhotos() {


    }


    companion object {
        private var INSTANCE: PhotoRemoteDataSource? = null

        @JvmStatic
        fun getInstance(
            compositeDisposable: CompositeDisposable,
            api: ExomindService
        ): PhotoRemoteDataSource {
            if (INSTANCE == null) {
                synchronized(PhotoRemoteDataSource::javaClass) {
                    INSTANCE = PhotoRemoteDataSource(compositeDisposable,api)
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