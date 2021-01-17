package com.example.repositorylistapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.repositorylistapp.model.UserRepoModel

@Dao
interface RepositoryDao {
    @Insert
    suspend fun insertAll(vararg repositories: UserRepoModel): List<Long>

/*    @Query("SELECT * FROM userrepomodel")
    suspend fun getAllRepositories(user: String): List<UserRepoModel>*/

/*    @Query("SELECT * FROM userrepomodel WHERE uuid = :id")
    suspend fun getRepository(id: Int): UserRepoModel*/

}