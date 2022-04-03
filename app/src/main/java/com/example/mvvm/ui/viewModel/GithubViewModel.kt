package com.example.mvvm.ui.viewModel

import androidx.lifecycle.*
import com.example.mvvm.data.models.User
import com.example.mvvm.data.repos.GithubRepository
import kotlinx.coroutines.*

class GithubViewModel :ViewModel() {

    val users = MutableLiveData<List<User>>()
    fun fetchUsers() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) { GithubRepository.getUsers() }

            if (response.isSuccessful) {
                response.body()?.let {
                    users.postValue(it)
                }
            }
        }
    }

    fun searchUsers(query: String) = liveData(Dispatchers.IO) {
        runIO {
            val response = withContext(Dispatchers.IO) { GithubRepository.searchUsers(query) }

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it.items)
                }
            }
        }
    }

}
    // Extension function for viewModel scope
    fun ViewModel.runIO(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        function: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            function()
        }
    }
