package noblur.com.exomindtest.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(

    @PrimaryKey(autoGenerate = true)
                 var id:Int,
                 var userId: Int,
                 var title: String
    )