package com.example.repositorylistapp.service


import com.example.repositorylistapp.model.UserRepoModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RepoApiService {
    private companion object {
        private const val BASE_URL = "https://api.github.com"
    }

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RepoApi::class.java)

   fun getUserRepos(user: String?): Single<List<UserRepoModel>> = api.getUserRepos(user)
}