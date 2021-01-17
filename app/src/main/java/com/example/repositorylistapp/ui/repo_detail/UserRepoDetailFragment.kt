package com.example.repositorylistapp.ui.repo_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.repositorylistapp.R
import com.example.repositorylistapp.databinding.FragmentUserRepoDetailBinding

class UserRepoDetailFragment : Fragment () {

    private lateinit var viewModel: UserRepoDetailViewModel
    private lateinit var binding: FragmentUserRepoDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_repo_detail, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserRepoDetailViewModel::class.java)
        arguments?.let { viewModel.handleRepositoryDetail(UserRepoDetailFragmentArgs.fromBundle(it).repository) }
        binding.viewModel = viewModel
    }
}