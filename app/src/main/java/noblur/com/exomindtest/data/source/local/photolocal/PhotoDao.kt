package noblur.com.exomindtest.data.source.local.photolocal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.Photo
import noblur.com.exomindtest.data.entities.User

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo WHERE albumId =:albumId")
    fun getPhotosByAlbumId(albumId: Int): List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photos: List<Photo>)


    @Query("DELETE FROM photo")
    fun deleteAllPhotos()

}