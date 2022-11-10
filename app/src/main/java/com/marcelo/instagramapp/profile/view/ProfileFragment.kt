package com.marcelo.instagramapp.profile.view

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.marcelo.instagramapp.R
import com.marcelo.instagramapp.common.base.BaseFragment
import com.marcelo.instagramapp.common.model.Post
import com.marcelo.instagramapp.common.model.UserAuth
import com.marcelo.instagramapp.databinding.FragmentProfileBinding
import com.marcelo.instagramapp.profile.Profile

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, Profile.Presenter>(
        R.layout.fragment_profile,
        FragmentProfileBinding::bind
    ), Profile.View {

    override lateinit var presenter: Profile.Presenter

    private var adapter = PostAdapter()

    override fun setupViews() {
        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRv?.adapter = adapter

        presenter.fetchUserProfile()
    }

    override fun setupPresenter() {
        //TODO: presenter = ProfilePresenter(this, repository)
    }

    override fun getMenu(): Int? {
        return R.menu.menu_profile
    }

    override fun showProgress(enabled: Boolean) {
        binding?.profileProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayUserProfile(userAuth: UserAuth) {
        binding?.profileTxtPostsCount?.text = userAuth.postCount.toString()
        binding?.profileTxtFollowinCount?.text = userAuth.followingCount.toString()
        binding?.profileTxtFollowersCount?.text = userAuth.followersCount.toString()
        binding?.profileTxtUsername?.text = userAuth.name
        binding?.profileTxtBio?.text = "TODO"

        presenter.fetchUserPosts()

    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun displayEmptyPost() {
        binding?.profileTxTEmpty?.visibility = View.VISIBLE
        binding?.profileRv?.visibility = View.GONE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun displayFullPosts(posts: List<Post>) {
        binding?.profileTxTEmpty?.visibility = View.GONE
        binding?.profileRv?.visibility = View.VISIBLE

        adapter.items = posts
        adapter.notifyDataSetChanged()
    }


}