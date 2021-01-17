package com.example.repositorylistapp.ui.user_repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repositorylistapp.R
import com.example.repositorylistapp.databinding.FragmentUserRepoListBinding
import com.example.repositorylistapp.model.UserRepoModel
import com.example.repositorylistapp.ui.user_repos.adapter.RepoListAdapter

class UserRepoListFragment : Fragment() {
    private lateinit var viewModel: UserRepoListViewModel
    private lateinit var binding: FragmentUserRepoListBinding

    private val repoListAdapter = RepoListAdapter(arrayListOf())
    private val repoListLiveDataObservers = Observer<List<UserRepoModel>> { list ->
        list?.let {
            repoListAdapter.submitList(ArrayList(list))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_repo_list, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UserRepoListViewModel::class.java).apply {
            binding.viewModel = this
            submitListToAdapterLiveData.observe(viewLifecycleOwner, repoListLiveDataObservers)
        }

        binding.repoList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoListAdapter
        }
    }


    override fun onResume() {
        super.onResume()

    }
}