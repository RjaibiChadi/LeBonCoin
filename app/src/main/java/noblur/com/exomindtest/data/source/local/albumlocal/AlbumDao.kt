package noblur.com.exomindtest.data.source.local.albumlocal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.User

@Dao
interface AlbumDao {




    @Query("SELECT * FROM album WHERE userId =:userId")
    fun getAlbumByUserId(userId: Int): List<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(albums: List<Album>)


    @Query("DELETE FROM album")
    fun deleteAllAlbums()

}