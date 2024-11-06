package com.my.github.presentation.Search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.my.github.presentation.Common.Views.LoadingView
import com.my.github.presentation.Search.views.SearchAppBarView
import com.my.github.presentation.Search.views.SearchErrorView
import com.my.github.presentation.Search.views.SearchInputView
import com.my.github.presentation.Search.views.SearchRepositoriesListView

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel(), onOpenDownloads: () -> Unit = {}) {

  Scaffold(
    topBar = { SearchAppBarView(onOpenDownloads) }
  ) { padding ->
    Column(modifier = Modifier.padding(padding)) {
      SearchInputView(viewModel)
      when (viewModel.state) {
        SearchViewModel.State.Error -> SearchErrorView(onRetry = viewModel::search)
        SearchViewModel.State.Searching -> LoadingView()
        SearchViewModel.State.Loaded -> SearchRepositoriesListView(viewModel)
      }
    }
  }
}
