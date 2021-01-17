package com.example.repositorylistapp.ui.user_repos

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repositorylistapp.model.UserRepoModel
import com.example.repositorylistapp.service.RepoApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class UserRepoListViewModel: ViewModel() {
    private var disposable = CompositeDisposable()
    private var apiService = RepoApiService()

    var progressBarVisibilityObservable = ObservableBoolean(false)
    var user = ObservableField<String>("")
    val submitListToAdapterLiveData = MutableLiveData<List<UserRepoModel>>()

    private var repoList: ArrayList<UserRepoModel>? = null

    fun getRepos() {
        progressBarVisibilityObservable.set(true)
        disposable.add(
            apiService.getUserRepos(user.get())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<UserRepoModel>>() {
                    override fun onSuccess(listResponse: List<UserRepoModel>) {
                        listResponse?.let {
                            repoList =  ArrayList(it)
                            submitListToAdapterLiveData.value = listResponse
                        }
                        progressBarVisibilityObservable.set(false)
                    }

                    override fun onError(e: Throwable) {
                        progressBarVisibilityObservable.set(false)
                    }
                })
        )
    }

    fun showProgress() = progressBarVisibilityObservable.get()

}