package com.my.github.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.my.github.domain.models.GithubDownloadedRepository

@Database(entities = [GithubDownloadedRepository::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
  abstract val dao: GithubDao
}