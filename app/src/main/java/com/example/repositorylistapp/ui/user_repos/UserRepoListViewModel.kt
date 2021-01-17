package com.example.repositorylistapp.ui.user_repos

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repositorylistapp.base.BaseViewModel
import com.example.repositorylistapp.model.UserRepoModel
import com.example.repositorylistapp.service.RepoApiService
import com.example.repositorylistapp.service.RepositoryDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class UserRepoListViewModel(application: Application): BaseViewModel(application) {
    private var disposable = CompositeDisposable()
    private var apiService = RepoApiService()

    var progressBarVisibilityObservable = ObservableBoolean(false)
    var user = ObservableField<String>("")
    val submitListToAdapterLiveData = MutableLiveData<List<UserRepoModel>>()
    var explanationText = ObservableField<String>("Please enter a username and press submit button to see someones repositories")
    var explanationTextVisibility = ObservableBoolean(true)
    var listVisibility = ObservableBoolean(false)


    private var repoList: ArrayList<UserRepoModel>? = null

    fun getRepos() {
        progressBarVisibilityObservable.set(true)
        explanationTextVisibility.set(false)
        listVisibility.set(false)
        disposable.add(
            apiService.getUserRepos(user.get())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<UserRepoModel>>() {
                    override fun onSuccess(listResponse: List<UserRepoModel>) {
                        storeInDb(listResponse)
                        listResponse?.let {
                            repoList =  ArrayList(it)
                            submitListToAdapterLiveData.value = listResponse
                        }
                        progressBarVisibilityObservable.set(false)
                        listVisibility.set(true)
                        explanationTextVisibility.set(false)
                    }

                    override fun onError(e: Throwable) {
                        progressBarVisibilityObservable.set(false)
                        explanationText.set("Occured an internal error. Please try again later!")
                        explanationTextVisibility.set(true)
                        listVisibility.set(false)

                    }
                })
        )
    }

    private fun storeInDb(listResponse : List<UserRepoModel>){
        launch {
            val dao = RepositoryDatabase(getApplication()).repositoryDao()
            val listLong = dao.insertAll(*listResponse.toTypedArray())
             var i = 0
            while (i<listResponse.size){
                listResponse[i].uuid = listLong[i].toInt()
                i += 1
            }
        }
    }

}