package noblur.com.leboncoin.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import noblur.com.leboncoin.data.entities.Album
import noblur.com.leboncoin.data.source.local.albumlocal.AlbumDao



@Database(entities = arrayOf(
    Album::class
   ),version = 1)
abstract class LeBonCoinDatabase: RoomDatabase() {



        abstract fun albumDao():AlbumDao


        companion object {

            private var INSTANCE : LeBonCoinDatabase?=null

            private val lock = Any()

            fun getInstance(context: Context): LeBonCoinDatabase {
                synchronized(lock){
                    if (INSTANCE ==null){

                            INSTANCE = Room.databaseBuilder(context.applicationContext,
                                LeBonCoinDatabase::class.java,"leboncoin.db")
                                .fallbackToDestructiveMigration()
                                .build()
                    }

                    return INSTANCE!!


                }
            }
        }

}