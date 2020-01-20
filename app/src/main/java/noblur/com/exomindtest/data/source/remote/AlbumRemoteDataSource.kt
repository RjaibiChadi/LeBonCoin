package noblur.com.exomindtest.data.source.remote

import android.util.Log
import androidx.annotation.VisibleForTesting
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.repository.AlbumDataSource
import noblur.com.exomindtest.data.repository.UserDataSource


class AlbumRemoteDataSource(
    val compositeDisposable: CompositeDisposable,
    val api: ExomindService
) : AlbumDataSource {

    override fun getAlbumsByUserId(userId: Int, callback: AlbumDataSource.GetAlbumsCallback) {

        compositeDisposable?.add(api.getAlbums(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {it->
                    callback.onAlbumsLoaded(it)},
                { error ->

                    callback.onDataNotAvailable(500)
                }
            )
        )
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