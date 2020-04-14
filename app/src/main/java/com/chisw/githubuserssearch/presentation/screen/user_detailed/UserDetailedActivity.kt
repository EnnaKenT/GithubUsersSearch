package com.chisw.githubuserssearch.presentation.screen.user_detailed

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.observe
import com.chisw.githubuserssearch.R
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.presentation.screen.base.activity.BaseActivity
import com.chisw.githubuserssearch.presentation.utils.customViewModel
import com.chisw.githubuserssearch.presentation.utils.show
import kotlinx.android.synthetic.main.activity_user_detailed.*

class UserDetailedActivity : BaseActivity(R.layout.activity_user_detailed) {

    private val viewModel by customViewModel<UserDetailedViewModel>()

    override fun initViews() {
        val masterUser = intent.getParcelableExtra<User>(MASTER_USER_ARG)
        initToolbar(masterUser.login)
        viewModel.requestDataForMasterUser(masterUser)
        viewModel.userFollowersLiveData.observe(this, ::setFollowers)
        viewModel.userPublicReposLiveData.observe(this, ::setRepos)
        viewModel.failure.observe(this) {
            hideProgressBar()
            showToast(it)
        }
    }

    private fun initToolbar(masterLogin: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        toolbarTextView.text = masterLogin
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setFollowers(count: Int) {
        followersTv.text = getString(R.string.followers_format, count.toString())
        followersTv.show(true)
        hideProgressBar()
    }

    private fun setRepos(count: Int) {
        publicReposTv.text = getString(R.string.public_repositories_format, count.toString())
        publicReposTv.show(true)
        hideProgressBar()
    }

    private fun hideProgressBar() = progressBar.show(false)

    companion object {
        private const val MASTER_USER_ARG = "master_user"

        fun startActivity(activity: Activity, masterUser: User) {
            val intent = Intent(activity, UserDetailedActivity::class.java).apply {
                putExtra(MASTER_USER_ARG, masterUser)
            }
            activity.startActivity(intent)
        }
    }

}