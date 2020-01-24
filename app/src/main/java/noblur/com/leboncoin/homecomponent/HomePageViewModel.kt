package noblur.com.leboncoin.homecomponent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import noblur.com.leboncoin.data.entities.Album
import noblur.com.leboncoin.data.entities.Event
import noblur.com.leboncoin.data.repository.AlbumDataSource
import noblur.com.leboncoin.data.repository.AlbumRepository


class HomePageViewModel(
    val albumRepository: AlbumRepository
) : ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    val empty: LiveData<Boolean>
        get() = _empty

    private val _empty = MutableLiveData<Boolean>()


    fun start() {

        loadAlbums()
    }

    private fun loadAlbums() {

        albumRepository.getAlbums(object :AlbumDataSource.GetAlbumsCallback{
            override fun onAlbumsLoaded(albums: List<Album>) {
                _albums.postValue(albums)

            }

            override fun onDataNotAvailable(code: Int) {

                _empty.value = false
            }


        })
    }




}
