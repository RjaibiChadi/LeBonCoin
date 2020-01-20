package noblur.com.exomindtest.data.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name: String,
    var username: String,
    var email: String,
    var phone:String,
    var website:String
)