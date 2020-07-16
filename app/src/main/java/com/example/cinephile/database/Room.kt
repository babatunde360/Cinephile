package com.example.cinephile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    DatabaseMovieResultsItem::class
    ,DatabaseAiringTodaySeriesItem::class
    ,DatabasePopularSeriesItem::class
    ,DatabaseUpComingMovieResultItem::class]
    ,version = 1,exportSchema = false)
 abstract class MovieItemResultDatabase : RoomDatabase(){
    abstract fun cinephileDao(): CinephileDao

    companion object{
        @Volatile
        private var INSTANCE : MovieItemResultDatabase? = null

        fun getDatabase(context: Context):
                MovieItemResultDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieItemResultDatabase::class.java,
                    "databasemovie"
                ).build()
                INSTANCE =  instance
                return instance
            }
        }
    }
}