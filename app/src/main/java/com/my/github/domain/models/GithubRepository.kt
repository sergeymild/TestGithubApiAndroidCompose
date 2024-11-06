package com.my.github.domain.models

import com.google.gson.annotations.SerializedName



data class GithubRepository(
  @SerializedName("name")
  val name: String,
  @SerializedName("full_name")
  val fullName: String,
  @SerializedName("id")
  val id: Int,
  @SerializedName("description")
  val description: String?,
  @SerializedName("html_url")
  val htmlUrl: String,
  @SerializedName("owner")
  val owner: GithubRepositoryOwner,
  @SerializedName("default_branch")
  val defaultBranch: String,
  var isDownloading: Boolean = false,
  var isError: Boolean = false,
)

data class GithubRepositoryOwner(
  @SerializedName("login")
  val login: String
)