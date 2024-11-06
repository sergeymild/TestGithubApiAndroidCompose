package com.my.github.presentation.Downloaded

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.my.github.presentation.Common.Views.LoadingView
import com.my.github.presentation.Downloaded.views.DownloadedAppBarView
import com.my.github.presentation.Downloaded.views.DownloadedRepositoriesListView

@Composable
fun DownloadedScreen(viewModel: DownloadedViewModel = hiltViewModel(), goBack: () -> Unit = {}) {

  LaunchedEffect(key1 = Unit) {
    viewModel.onEvent(DownloadedViewModel.Event.Load)
  }

  Scaffold(
    topBar = { DownloadedAppBarView(goBack) }
  ) { padding ->
    Column(modifier = Modifier.padding(padding)) {
      when (viewModel.state) {
        DownloadedViewModel.State.Loading -> LoadingView()
        DownloadedViewModel.State.Loaded -> DownloadedRepositoriesListView(viewModel)
      }
    }
  }
}