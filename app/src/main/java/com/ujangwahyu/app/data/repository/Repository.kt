package com.ujangwahyu.app.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.ujangwahyu.app.data.entity.UserEntity
import com.ujangwahyu.app.data.model.UserResponse
import kotlinx.coroutines.CoroutineScope

interface Repository {

    fun searchUser(
        keyword: String,
        scope: CoroutineScope,
        callback: MutableLiveData<PagingData<UserResponse.UserItem>>
    )

    fun getUser(
        scope: CoroutineScope,
        callback: MutableLiveData<PagingData<UserEntity>>
    )

    fun insertUser(data: UserEntity, onSuccess: (Boolean) -> Unit)

    fun updateUser(data: UserEntity, onSuccess: (Boolean) -> Unit)

    fun deleteUser(id: Int, onSuccess: (Boolean) -> Unit)
}