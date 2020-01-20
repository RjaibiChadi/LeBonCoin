package noblur.com.exomindtest.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import noblur.com.exomindtest.data.entities.Album
import noblur.com.exomindtest.data.entities.Photo
import noblur.com.exomindtest.data.entities.User
import noblur.com.exomindtest.data.source.local.albumlocal.AlbumDao
import noblur.com.exomindtest.data.source.local.photolocal.PhotoDao
import noblur.com.exomindtest.data.source.local.userlocal.UserDao

@Database(entities = arrayOf(
    User::class,
    Album::class,
    Photo::class
   ),version = 1)
abstract class ExomindDatabase: RoomDatabase() {


        abstract fun userDao(): UserDao
        abstract fun albumDao():AlbumDao
        abstract fun photoDao():PhotoDao

        companion object {

            private var INSTANCE : ExomindDatabase?=null

            private val lock = Any()

            fun getInstance(context: Context): ExomindDatabase {
                synchronized(lock){
                    if (INSTANCE ==null){

                            INSTANCE = Room.databaseBuilder(context.applicationContext,
                                ExomindDatabase::class.java,"exomind.db")
                                .fallbackToDestructiveMigration()
                                .build()
                    }

                    return INSTANCE!!


                }
            }
        }

}