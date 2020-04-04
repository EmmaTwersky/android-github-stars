package com.emma.star.view

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emma.star.R
import com.emma.star.databinding.RepoItemViewBinding
import com.emma.star.model.Repo

class RepoAdapter(private var repoList: ArrayList<Repo?>) :
    RecyclerView.Adapter<RepoViewHolder>() {

    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RepoViewHolder {
        context = parent.context

        val repoViewBinding: RepoItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.repo_item_view, parent,
            false
        )

        return RepoViewHolder(repoViewBinding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo: Repo? = repoList[position]
        holder.binding.repo = repo
        holder.binding.cardView.setOnClickListener {
            val customTabsIntent = CustomTabsIntent.Builder()
                .setToolbarColor(R.color.colorPrimaryDark)
                .setNavigationBarColor(R.color.colorPrimaryDark)
                .setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left)
                .setExitAnimations(context, R.anim.slide_in_left, R.anim.slide_out_right)
                .build()
            customTabsIntent.launchUrl(context, Uri.parse(repo?.url))
        }

        Glide.with(context)
            .load(Uri.parse(repo?.owner?.avatarUrl))
            .centerCrop()
            .placeholder(R.drawable.star)
            .error(R.drawable.star)
            .into(holder.binding.repoOwnerImage)
    }

    override fun getItemCount() = repoList.size
}