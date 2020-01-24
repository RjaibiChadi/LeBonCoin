package noblur.com.leboncoin

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import noblur.com.leboncoin.data.repository.AlbumRepository
import noblur.com.leboncoin.data.source.local.LeBonCoinDatabase
import noblur.com.leboncoin.data.source.local.albumlocal.AlbumLocalDataSource
import noblur.com.leboncoin.data.source.remote.*
import noblur.com.leboncoin.utils.AppExecutors


object Injection {

    fun provideAlbumRepository(context: Context): AlbumRepository {
        val database = LeBonCoinDatabase.getInstance(context)
        val api = RetrofitClient.instance.create(LeBonCoinService::class.java)
        return AlbumRepository.getInstance(
            AlbumRemoteDataSource.getInstance(CompositeDisposable(),api),
            AlbumLocalDataSource.getInstance(AppExecutors(), database.albumDao()))
    }

}