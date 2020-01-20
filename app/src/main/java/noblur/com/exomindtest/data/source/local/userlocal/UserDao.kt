package noblur.com.exomindtest.data.source.local.userlocal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import noblur.com.exomindtest.data.entities.User

@Dao
interface UserDao {


    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Query("SELECT * FROM user WHERE name LIKE '%' || :query || '%'")
    fun getUserByName(query: String): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: List<User>)


    @Query("DELETE FROM user")
    fun deleteAllUsers()

}