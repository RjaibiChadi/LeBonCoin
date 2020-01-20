package noblur.com.exomindtest.data.repository


import noblur.com.exomindtest.data.entities.Photo



interface PhotoDataSource {



    interface GetPhotosCallback {

        fun onPhotosLoaded(photos: List<Photo>)

        fun onDataNotAvailable(code:Int)
    }


    fun getPhotosByAlbumId(albumId:Int,callback: GetPhotosCallback)

    fun registerPhotos(photos: List<Photo>)

    fun deleteAllPhotos()

}