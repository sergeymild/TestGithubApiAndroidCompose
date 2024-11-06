package com.my.github.di

import android.content.Context
import androidx.room.Room
import com.my.github.data.local.AppDatabase
import com.my.github.data.local.GithubDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(
      context = context,
      klass = AppDatabase::class.java,
      name = "github.db"
    ).build()
  }

  @Provides
  @Singleton
  fun provideGithubDao(database: AppDatabase): GithubDao {
    return database.dao
  }
}