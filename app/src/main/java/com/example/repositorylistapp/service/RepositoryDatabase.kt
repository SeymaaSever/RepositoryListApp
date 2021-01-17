package com.example.repositorylistapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.repositorylistapp.model.UserRepoModel

@Database(entities = arrayOf(UserRepoModel::class),version = 3 )
@TypeConverters(Converters::class)
abstract class RepositoryDatabase : RoomDatabase(){
    abstract fun repositoryDao() : RepositoryDao

    companion object{
       @Volatile private var instance: RepositoryDatabase? = null
        private val lock = Any()
        operator fun  invoke(context: Context) = instance?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, RepositoryDatabase::class.java,"repositorydatabase"
        ).fallbackToDestructiveMigration().build()
    }
}