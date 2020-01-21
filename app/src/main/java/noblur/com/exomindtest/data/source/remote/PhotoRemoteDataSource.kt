package noblur.com.exomindtest.data.source.remote

import android.util.Log
import androidx.annotation.VisibleForTesting
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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
        compositeDisposable?.add(api.getPhotos(albumId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {it->
                    callback.onPhotosLoaded(it)
                    Log.i("Albumremote",it.size.toString())

                },
                { error ->

                    callback.onDataNotAvailable(500)
                }
            )
        )
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