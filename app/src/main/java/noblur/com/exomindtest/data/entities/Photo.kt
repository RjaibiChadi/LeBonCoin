package noblur.com.exomindtest.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Photo(

            @PrimaryKey(autoGenerate = true)
             var id:Int,
             var albumId: Int,
             var title: String,
             var url: String,
             var thumbnailUrl: String
)