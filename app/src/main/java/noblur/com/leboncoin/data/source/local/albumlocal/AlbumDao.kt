package noblur.com.leboncoin.data.source.local.albumlocal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import noblur.com.leboncoin.data.entities.Album

@Dao
interface AlbumDao {




    @Query("SELECT * FROM album")
    fun getAlbums(): List<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(albums: List<Album>)


    @Query("DELETE FROM album")
    fun deleteAllAlbums()

}