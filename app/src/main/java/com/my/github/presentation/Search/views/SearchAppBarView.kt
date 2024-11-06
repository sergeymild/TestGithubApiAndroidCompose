package com.my.github.presentation.Search.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBarView(onOpenDownloads: () -> Unit = {}) {
  TopAppBar(
    actions = {
      IconButton(onClick = onOpenDownloads) {
        Icon(
          imageVector = Icons.Filled.Favorite,
          contentDescription = "Downloads"
        )
      }
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer,
      titleContentColor = MaterialTheme.colorScheme.primary,
    ),
    title = {
      Text("Search page")
    }
  )
}