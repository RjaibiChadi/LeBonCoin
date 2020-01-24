package noblur.com.leboncoin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.collect.Lists
import com.nhaarman.mockitokotlin2.capture
import noblur.com.leboncoin.data.entities.Album
import noblur.com.leboncoin.data.repository.AlbumDataSource
import noblur.com.leboncoin.data.repository.AlbumRepository
import noblur.com.leboncoin.homecomponent.HomePageViewModel
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class HomePageViewModelTest {


    @get:Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var albumRepository: AlbumRepository
    @Captor
    private lateinit var loadAlbumsCallbackCaptor:
            ArgumentCaptor<AlbumDataSource.GetAlbumsCallback>

    private lateinit var homePageViewModel: HomePageViewModel
    private lateinit var albums: MutableList<Album>


    @Before fun setupHomePageViewModel(){

        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        homePageViewModel = HomePageViewModel(albumRepository)

        val user1 = Album(1,"title1","url1","thumbnailurl")
        val user2 = Album(2,"title2","url2","thumbnailurl")
        val user3 = Album(3,"title3","url3","thumbnailurl")


        albums = Lists.newArrayList(user1,user2,user3)
    }

   @Test fun loadEmptyUsersFromRepository_EmptyResults(){

       albums.clear()

       homePageViewModel.start()

       verify<AlbumRepository>(albumRepository).getAlbums(capture(loadAlbumsCallbackCaptor))
       loadAlbumsCallbackCaptor.value.onAlbumsLoaded(albums)

       MatcherAssert.assertThat(LiveDataTestUtil.getValue(homePageViewModel.empty), Is.`is`(true))


    }
}