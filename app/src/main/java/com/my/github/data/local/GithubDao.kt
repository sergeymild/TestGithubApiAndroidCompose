package com.my.github.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.my.github.domain.models.GithubDownloadedRepository

@Dao
interface GithubDao {
  @Query("SELECT * FROM repositories")
  suspend fun findAll(): List<GithubDownloadedRepository>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(item: GithubDownloadedRepository)

  @Query("DELETE FROM repositories WHERE id = :id")
  suspend fun deleteById(id: Int)
}