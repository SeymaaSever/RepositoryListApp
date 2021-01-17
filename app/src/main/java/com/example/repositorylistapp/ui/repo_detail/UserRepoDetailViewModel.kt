package com.example.repositorylistapp.ui.repo_detail

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.example.repositorylistapp.model.UserRepoModel
import com.example.repositorylistapp.service.RepoApiService
import io.reactivex.disposables.CompositeDisposable

class UserRepoDetailViewModel : ViewModel() {
    private var disposable = CompositeDisposable()
    private var apiService = RepoApiService()


    var userImageUrlObservable = ObservableField<String>()
    var ownerNameObservable = ObservableField<String>()
    var descriptionObservable = ObservableField<String>()
    var starCountObservable = ObservableField<String>()
    var issueCountObservable = ObservableField<String>()
    var homePageObservable = ObservableField<String>()

    fun handleRepositoryDetail(response: UserRepoModel?) {
        response?.run {
            userImageUrlObservable.set(response.owner?.url)
            ownerNameObservable.set("Repository Name: $name")
            descriptionObservable.set("Description: $description")
            homePageObservable.set("Home Page: $homepage")
            startCount?.let { starCountObservable.set("Stars:$it") }
            issueCount?.let { issueCountObservable.set("Open Issues:$it") }
        }
    }
}