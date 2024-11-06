package com.my.github.data.remote

import com.my.github.domain.models.GithubRepositoryResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface GithubApi {

  @GET("/search/repositories")
  suspend fun searchRepositories(@Query("q") searchQuery: String): GithubRepositoryResponse

  @Streaming
  @GET
  suspend fun downloadFile(@Url fileUrl: String): ResponseBody
}