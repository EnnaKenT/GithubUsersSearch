package com.chisw.githubuserssearch.data.network.api

sealed class Http {
    object Query : Http() {
        internal const val TYPE = "users"
        internal const val SORT = "stars"
        internal const val ORDER = "desc"
        internal const val PER_PAGE = 100
    }

    object Path : Http() {
        internal const val LOGIN = "login"
    }
}