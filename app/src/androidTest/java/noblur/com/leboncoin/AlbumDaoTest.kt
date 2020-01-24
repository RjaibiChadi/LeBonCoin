package noblur.com.leboncoin

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import noblur.com.leboncoin.data.entities.Album
import noblur.com.leboncoin.data.source.local.LeBonCoinDatabase
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class) class AlbumDaoTest {

    private lateinit var database : LeBonCoinDatabase

    @Before
    fun initDb(){

        database = Room.inMemoryDatabaseBuilder(androidx.test.InstrumentationRegistry.getContext(),
            LeBonCoinDatabase::class.java).build()

    }

    @After
    fun closeDb() = database.close()

    @Test fun insertLeagueAndGetById(){

        database.albumDao().insertAlbum(albums)

        val loaded =database.albumDao().getAlbums()

        MatcherAssert.assertThat(loaded!!.size, `is`(1))


    }
    @Test fun deleteAllLeagueAndGettingTasks() {
        // Given a league inserted
        database.albumDao().insertAlbum(albums)

        // When deleting all league
        database.albumDao().deleteAllAlbums()

        // When getting the leagues
        val leagues = database.albumDao().getAlbums()

        // The list is empty
        assertThat(leagues.size, `is`(0))
    }




    companion object {

        private val DEFAULT_ID = 1
        private val DEFAULT_TITLE = "title"
        private val DEFAULT_URL = "url"
        private val DEFAULT_THUMBNAIL_URL = "thumbnailUrl"
        private val DEFAULT_LEAGUE = Album(DEFAULT_ID,DEFAULT_TITLE,DEFAULT_URL, DEFAULT_THUMBNAIL_URL)


        private val albums : List<Album> = mutableListOf(DEFAULT_LEAGUE,DEFAULT_LEAGUE)
    }
}
