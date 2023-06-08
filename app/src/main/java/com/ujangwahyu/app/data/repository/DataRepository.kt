package com.ujangwahyu.app.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.ujangwahyu.app.data.entity.UserEntity
import com.ujangwahyu.app.data.model.UserResponse
import com.ujangwahyu.app.data.repository.local.LocalRepository
import com.ujangwahyu.app.data.repository.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
): Repository {

    override fun searchUser(
        keyword: String,
        scope: CoroutineScope,
        callback: MutableLiveData<PagingData<UserResponse.UserItem>>,
    ) {
        remoteRepository.searchUser(keyword, scope, callback)
    }

    override fun getUser(
        scope: CoroutineScope,
        callback: MutableLiveData<PagingData<UserEntity>>
    ) {
        localRepository.getUser(scope, callback)
    }

    override fun insertUser(data: UserEntity, onSuccess: (Boolean) -> Unit) {
        localRepository.insertUser(data, onSuccess)
    }

    override fun updateUser(data: UserEntity, onSuccess: (Boolean) -> Unit) {
        localRepository.updateUser(data, onSuccess)
    }

    override fun deleteUser(id: Int, onSuccess: (Boolean) -> Unit) {
        localRepository.deleteUser(id, onSuccess)
    }

    override fun clearDisposable() {
        localRepository.clearDisposable()
        remoteRepository.clearDisposable()
    }
}