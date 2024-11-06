package com.my.github.presentation.Search.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.my.github.presentation.Search.SearchViewModel

@Composable
fun SearchInputView(viewModel: SearchViewModel) {
  val keyboardController = LocalSoftwareKeyboardController.current

  TextField(
    value = viewModel.searchQuery,
    onValueChange = { viewModel.searchQuery = it },
    label = { Text("Type to search") },
    placeholder = { Text("Type to search") },
    singleLine = true,
    modifier = Modifier.fillMaxWidth(),
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Text,
      imeAction = ImeAction.Search
    ),
    keyboardActions = KeyboardActions(onSearch = {
      keyboardController?.hide()
      viewModel.onEvent(SearchViewModel.Event.Search)
    })

  )
}