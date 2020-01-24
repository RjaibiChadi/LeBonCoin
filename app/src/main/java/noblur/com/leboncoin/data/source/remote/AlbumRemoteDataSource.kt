package noblur.com.leboncoin.data.source.remote

import androidx.annotation.VisibleForTesting
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import noblur.com.leboncoin.data.entities.Album
import noblur.com.leboncoin.data.repository.AlbumDataSource


class AlbumRemoteDataSource(
    val compositeDisposable: CompositeDisposable,
    val api: LeBonCoinService
) : AlbumDataSource {



    override fun getAlbums(callback: AlbumDataSource.GetAlbumsCallback) {
        compositeDisposable?.add(api.getAlbums()
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
            api: LeBonCoinService
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