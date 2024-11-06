package com.my.github.presentation.Search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.github.data.local.GithubDao
import com.my.github.data.remote.GithubApi
import com.my.github.domain.models.GithubDownloadedRepository
import com.my.github.domain.models.GithubRepository
import com.my.github.extensions.saveFileToDownloads
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
  private val api: GithubApi,
  private val dao: GithubDao
) : ViewModel() {
  sealed class Event {
    data object Search : Event()
    data class OnDownload(val index: Int) : Event()
  }

  sealed class State {
    data object Searching : State()
    data object Loaded : State()
    data object Error : State()
  }


  var searchQuery by mutableStateOf("")
  var repositories = mutableStateListOf<GithubRepository>()
    private set
  var state by mutableStateOf<State>(State.Searching)

  fun onEvent(event: Event) {
    when (event) {
      Event.Search -> search()
      is Event.OnDownload -> {
        downloadFile(event.index)
      }
    }
  }

  fun search() {
    state = State.Searching
    viewModelScope.launch {
      try {
        val response = api.searchRepositories(searchQuery)
        repositories.addAll(response.items)
        state = State.Loaded
      } catch (e: Throwable) {
        println(e.printStackTrace())
        state = State.Error
      }
    }
  }

  private fun downloadFile(index: Int) {
    val repo = repositories[index]
    repositories[index] = repo.copy(isDownloading = true, isError = false)

    viewModelScope.launch {
      try {
        val response = api.downloadFile("/repos/${repo.fullName}/zipball/${repo.defaultBranch}")
        val fileName = "${repo.fullName.replace("/", "_")}_${repo.defaultBranch}.zip"
        val savedPath = response.saveFileToDownloads(fileName)
        dao.insert(
          GithubDownloadedRepository(
            name = repo.name,
            ownerName = repo.owner.login,
            htmlUrl = repo.htmlUrl,
            path = savedPath,
            id = repo.id
          )
        )
        repositories[index] = repo.copy(isDownloading = false)
      } catch (e: Throwable) {
        println(e.printStackTrace())
        repositories[index] = repo.copy(isDownloading = false, isError = true)
      }
    }
  }
}