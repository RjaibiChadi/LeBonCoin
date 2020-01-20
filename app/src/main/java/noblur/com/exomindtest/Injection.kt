package noblur.com.exomindtest

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import noblur.com.exomindtest.data.repository.AlbumRepository
import noblur.com.exomindtest.data.repository.PhotoRepository
import noblur.com.exomindtest.data.repository.UserRepository
import noblur.com.exomindtest.data.source.local.ExomindDatabase
import noblur.com.exomindtest.data.source.local.albumlocal.AlbumLocalDataSource
import noblur.com.exomindtest.data.source.local.photolocal.PhotoLocalDataSource
import noblur.com.exomindtest.data.source.local.userlocal.UserLocalDataSource
import noblur.com.exomindtest.data.source.remote.*
import noblur.com.exomindtest.utils.AppExecutors


object Injection {

    fun provideUserRepository(context: Context): UserRepository {
        val database = ExomindDatabase.getInstance(context)
        val api = RetrofitClient.instance.create(ExomindService::class.java)

        return UserRepository.getInstance(
            UserRemoteDataSource.getInstance(CompositeDisposable(),api),
            UserLocalDataSource.getInstance(AppExecutors(), database.userDao()))
    }

    fun provideAlbumRepository(context: Context): AlbumRepository {
        val database = ExomindDatabase.getInstance(context)
        val api = RetrofitClient.instance.create(ExomindService::class.java)
        return AlbumRepository.getInstance(
            AlbumRemoteDataSource.getInstance(CompositeDisposable(),api),
            AlbumLocalDataSource.getInstance(AppExecutors(), database.albumDao()))
    }

    fun providePhotoRepository(context: Context): PhotoRepository {
        val database = ExomindDatabase.getInstance(context)
        val api = RetrofitClient.instance.create(ExomindService::class.java)

        return PhotoRepository.getInstance(
            PhotoRemoteDataSource.getInstance(CompositeDisposable(),api),
            PhotoLocalDataSource.getInstance(AppExecutors(), database.photoDao()))
    }



}