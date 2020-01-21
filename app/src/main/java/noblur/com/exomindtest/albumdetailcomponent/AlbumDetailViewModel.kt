package noblur.com.exomindtest.albumdetailcomponent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.Event
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.repository.AlbumDataSource
import noblur.com.exomindtest.data.repository.AlbumRepository

class AlbumDetailViewModel(
    val albumRepository: AlbumRepository)
    : ViewModel() {

    private val _albums = MutableLiveData<List<Album>>().apply { value = emptyList() }
    val albums: LiveData<List<Album>>
        get() = _albums

    private val _openPhotoEvent = MutableLiveData<Event<Int>>()
    val openPhotoEvent: LiveData<Event<Int>>
        get() = _openPhotoEvent

    fun start(userId: Int) {

        albumRepository.getAlbumsByUserId(userId,object :AlbumDataSource.GetAlbumsCallback{
            override fun onAlbumsLoaded(albums: List<Album>) {
                _albums.postValue(albums)

            }

            override fun onDataNotAvailable(code: Int) {


            }


        })

    }

    fun showPhotos(albumId: Int) {
        _openPhotoEvent.value = Event(albumId)

    }

}
