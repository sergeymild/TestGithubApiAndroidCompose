package com.my.github.data.remote

import com.my.github.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val newRequest = originalRequest.newBuilder()
      .addHeader("Authorization", "Bearer ${Constants.githubToken}")
      .build()
    return chain.proceed(newRequest)
  }
}