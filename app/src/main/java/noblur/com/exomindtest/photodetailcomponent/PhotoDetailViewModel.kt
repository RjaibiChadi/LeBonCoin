package noblur.com.exomindtest.photodetailcomponent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.Photo
import noblur.com.exomindtest.data.repository.PhotoDataSource
import noblur.com.exomindtest.data.repository.PhotoRepository

class PhotoDetailViewModel(

  val photoRepository: PhotoRepository
) : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>().apply { value = emptyList() }
    val photos: LiveData<List<Photo>>
        get() = _photos

    fun start(albumId: Int) {

        photoRepository.getPhotosByAlbumId(albumId,object :PhotoDataSource.GetPhotosCallback{
            override fun onPhotosLoaded(photos: List<Photo>) {
                _photos.postValue(photos)


            }

            override fun onDataNotAvailable(code: Int) {


            }


        })
    }


}
