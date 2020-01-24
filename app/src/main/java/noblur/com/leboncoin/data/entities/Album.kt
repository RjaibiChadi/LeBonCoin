package noblur.com.leboncoin.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(

    @PrimaryKey(autoGenerate = true)
                 var id:Int,
                 var title: String,
                 var url: String,
                 var thumbnailUrl: String
    )