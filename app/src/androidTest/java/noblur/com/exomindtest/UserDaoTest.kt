package noblur.com.exomindtest

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.source.local.ExomindDatabase
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
@RunWith(AndroidJUnit4::class) class UserDaoTest {

    private lateinit var database : ExomindDatabase

    @Before
    fun initDb(){

        database = Room.inMemoryDatabaseBuilder(androidx.test.InstrumentationRegistry.getContext(),
            ExomindDatabase::class.java).build()

    }

    @After
    fun closeDb() = database.close()

    @Test fun insertLeagueAndGetById(){

        database.userDao().insertUser(DEFAULT_LEAGUE)

        val loaded =database.userDao().getUserByName(DEFAULT_NAME)

        MatcherAssert.assertThat(loaded!!.size, `is`(1))


    }
    @Test fun deleteAllLeagueAndGettingTasks() {
        // Given a league inserted
        database.userDao().insertUser(DEFAULT_LEAGUE)

        // When deleting all league
        database.userDao().deleteAllUsers()

        // When getting the leagues
        val leagues = database.userDao().getUsers()

        // The list is empty
        assertThat(leagues.size, `is`(0))
    }




    companion object {

        private val DEFAULT_ID = 1
        private val DEFAULT_NAME = "la liga"
        private val DEFAULT_USER_NAME = "1234"
        private val DEFAULT_EMAIL = "soccer"
        private val DEFAULT_PHONE= "la liga"
        private val DEFAULT_WEBSITE= "la liga"
        private val DEFAULT_LEAGUE = User(DEFAULT_ID,DEFAULT_NAME,DEFAULT_USER_NAME, DEFAULT_EMAIL,DEFAULT_PHONE,DEFAULT_WEBSITE)


    }
}
