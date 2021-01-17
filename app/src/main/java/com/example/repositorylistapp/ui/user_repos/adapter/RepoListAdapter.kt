package com.example.repositorylistapp.ui.user_repos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.repositorylistapp.R
import com.example.repositorylistapp.model.UserRepoModel
import com.example.repositorylistapp.ui.user_repos.UserRepoListFragmentDirections
import kotlinx.android.synthetic.main.item_repo_row_list.view.*

class RepoListAdapter(private var repoList: ArrayList<UserRepoModel>):
    RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>(){

    class RepoViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_repo_row_list, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount() = repoList.size


    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repository = repoList[position]
        holder.view.apply {
            repo_name.text = repository.name
            container.setOnClickListener {
                val action = UserRepoListFragmentDirections.actionUserRepoListFragmentToUserRepoDetailFragment(repository)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    fun submitList(list: ArrayList<UserRepoModel>) {
        this.repoList = list
        notifyDataSetChanged()
    }
}