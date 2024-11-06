package com.my.github.presentation.Downloaded

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.my.github.data.local.GithubDao
import com.my.github.domain.models.GithubDownloadedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadedViewModel @Inject constructor(private val dao: GithubDao): ViewModel() {
  sealed class Event {
    data object Load : Event()
    data class OnDelete(val index: Int) : Event()
  }

  sealed class State {
    data object Loading : State()
    data object Loaded : State()
  }

  var repositories = mutableStateListOf<GithubDownloadedRepository>()
    private set
  var state by mutableStateOf<State>(State.Loading)
    private set

  fun onEvent(event: Event) {
    when (event) {
      is Event.OnDelete -> deleteRepository(event.index)
      Event.Load -> fetchAll()
    }
  }

  private fun fetchAll() {
    viewModelScope.launch {
      repositories.addAll(dao.findAll())
      state = State.Loaded
    }
  }

  private fun deleteRepository(index: Int) {
    viewModelScope.launch {
      dao.deleteById(repositories[index].id)
      repositories.removeAt(index)
    }
  }
}