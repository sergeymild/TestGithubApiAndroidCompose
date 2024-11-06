package com.my.github.domain.models

import com.google.gson.annotations.SerializedName

data class GithubRepositoryResponse(@SerializedName("items") val items: List<GithubRepository>)