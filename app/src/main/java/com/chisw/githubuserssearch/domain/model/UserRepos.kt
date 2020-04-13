package com.chisw.githubuserssearch.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRepos(
    val id: Int,
    val name: String,
    val private: Boolean
) : Parcelable