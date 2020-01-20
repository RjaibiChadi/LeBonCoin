package noblur.com.exomindtest.data.repository

import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.Photo
import noblur.com.exomindtest.data.entities.User


class PhotoRepository(
    val photoRemoteDataSource: PhotoDataSource,
    val photoLocalDataSource: PhotoDataSource
): PhotoDataSource {

    override fun getPhotosByAlbumId(albumId: Int, callback: PhotoDataSource.GetPhotosCallback) {

    }

    override fun registerPhotos(photos: List<Photo>) {

    }

    override fun deleteAllPhotos() {

    }




    companion object {

        private var INSTANCE: PhotoRepository? = null


        @JvmStatic fun getInstance(photoRemoteDataSource: PhotoDataSource,
                                   photoLocalDataSource: PhotoDataSource
        ) =
            INSTANCE
                ?: synchronized(PhotoRepository::class.java) {
                INSTANCE
                    ?: PhotoRepository(
                        photoRemoteDataSource,
                        photoLocalDataSource
                    )
                    .also { INSTANCE = it }
            }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }



}