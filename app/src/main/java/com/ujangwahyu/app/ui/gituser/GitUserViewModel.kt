package com.ujangwahyu.app.ui.gituser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ujangwahyu.app.data.model.UserResponse
import com.ujangwahyu.app.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GitUserViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {

    val users: MutableLiveData<PagingData<UserResponse.UserItem>> by lazy {
        MutableLiveData()
    }

    fun searchUsers(keyword: String) {
        repository.searchUser(keyword, viewModelScope, users)
    }

    override fun onCleared() {
        super.onCleared()
        repository.clearDisposable()
    }
}