package com.ujangwahyu.app.ui.myuser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ujangwahyu.app.data.entity.UserEntity
import com.ujangwahyu.app.data.model.UserResponse
import com.ujangwahyu.app.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyUserViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {

    val users: MutableLiveData<PagingData<UserEntity>> by lazy {
        MutableLiveData()
    }

    val insert: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }

    val update: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }

    val delete: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }

    fun getUser() {
        repository.getUser(viewModelScope, users)
    }

    fun insertUser(data: UserEntity) {
        repository.insertUser(data) {
            insert.postValue(it)
        }
    }

    fun updateUser(data: UserEntity) {
        repository.updateUser(data) {
            update.postValue(it)
        }
    }

    fun deleteUser(id: Int) {
        repository.deleteUser(id) {
            delete.postValue(it)
        }
    }

}