package com.my.github.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class GithubDownloadedRepository(
  @PrimaryKey(autoGenerate = false)
  val id: Int,
  val name: String,
  val ownerName: String,
  val htmlUrl: String,
  val path: String
)
