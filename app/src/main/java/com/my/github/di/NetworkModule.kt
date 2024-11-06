package com.my.github.di

import com.my.github.data.remote.AuthInterceptor
import com.my.github.data.remote.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  fun provideBaseUrl(): String = "https://api.github.com/"

  @Provides
  @Singleton
  fun provideRetrofit(baseUrl: String): Retrofit {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    val client: OkHttpClient = OkHttpClient.Builder()
      .addInterceptor(logging)
      .addInterceptor(AuthInterceptor())
      .build()

    return Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .client(client)
      .build()
  }

  @Provides
  @Singleton
  fun provideApiService(retrofit: Retrofit) = retrofit.create(GithubApi::class.java)

}