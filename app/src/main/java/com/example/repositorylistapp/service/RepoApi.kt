package com.example.repositorylistapp.service

import com.example.repositorylistapp.model.UserRepoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoApi {
    @GET("users/{user}/repos")
    fun getUserRepos(@Path("user") user: String?): Single<List<UserRepoModel>>

}