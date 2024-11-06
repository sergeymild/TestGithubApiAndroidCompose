package com.my.github.presentation.Downloaded.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.my.github.presentation.Downloaded.DownloadedViewModel

@Composable
fun DownloadedRepositoriesListView(viewModel: DownloadedViewModel) {
  val uriHandler = LocalUriHandler.current
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(vertical = 32.dp)
  ) {
    itemsIndexed(viewModel.repositories, key = { _, item -> item.id }) { index, it ->
      DownloadedRepositoriesListItemView(
        item = it,
        openUri = uriHandler::openUri,
        onDelete = {
          viewModel.onEvent(DownloadedViewModel.Event.OnDelete(index))
        })
    }
  }
}