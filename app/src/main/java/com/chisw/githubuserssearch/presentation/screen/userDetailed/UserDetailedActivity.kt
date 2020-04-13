package com.chisw.githubuserssearch.presentation.screen.userDetailed

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.observe
import com.chisw.githubuserssearch.R
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.presentation.screen.base.activity.BaseActivity
import com.chisw.githubuserssearch.presentation.utils.customViewModel
import com.chisw.githubuserssearch.presentation.utils.show
import com.chisw.githubuserssearch.presentation.viewModel.userDetailed.UserDetailedViewModel
import kotlinx.android.synthetic.main.activity_user_detailed.*

class UserDetailedActivity : BaseActivity(R.layout.activity_user_detailed) {

    private val viewModel by customViewModel<UserDetailedViewModel>()

    override fun initViews() {
        val masterUser = intent.getParcelableExtra<User>(MASTER_USER_ARG)
        viewModel.requestDataForMasterUser(masterUser)

        viewModel.userFollowersLiveData.observe(this, ::setFollowers)
        viewModel.userPublicReposLiveData.observe(this, ::setRepos)
        viewModel.failure.observe(this) { showToast(it) }
    }

    private fun setFollowers(count: Int) {
        if (count > 0) {
            followersTv.text = getString(R.string.followers_format, count.toString())
            followersTv.show(true)
            showProgressBar(false)
        }
    }

    private fun setRepos(count: Int) {
        if (count > 0) {
            publicReposTv.text = getString(R.string.public_repositories_format, count.toString())
            publicReposTv.show(true)
            showProgressBar(false)
        }
    }

    private fun showProgressBar(isVisible: Boolean) = progressBar.show(isVisible)

    companion object {
        private const val MASTER_USER_ARG = "master_user"

        fun startActivity(
            activity: Activity,
            masterUser: User
        ) {
            val intent = Intent(activity, UserDetailedActivity::class.java).apply {
                putExtra(MASTER_USER_ARG, masterUser)
            }
            activity.startActivity(intent)
        }
    }

}