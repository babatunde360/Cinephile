package com.example.cinephile.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Database(entities = [
    DatabaseMovieResultsItem::class
    ,DatabaseAiringTodaySeriesItem::class
    ,DatabaseUpComingMovieResultItem::class]
    ,version = 1,exportSchema = false)
public abstract class MovieItemResultDatabase : RoomDatabase(){
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