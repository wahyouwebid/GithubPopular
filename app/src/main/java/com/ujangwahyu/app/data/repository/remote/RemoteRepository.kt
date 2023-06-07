package com.ujangwahyu.app.data.repository.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import com.ujangwahyu.app.data.model.UserResponse
import com.ujangwahyu.app.data.paging.GitUserPagingSource
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteRepository @Inject constructor(
    private val gitUserPagingSource: GitUserPagingSource,
    private val disposable: CompositeDisposable,
    private val pagingConfig: PagingConfig
) {
    fun searchUser(
        keyword: String,
        scope: CoroutineScope,
        callback: MutableLiveData<PagingData<UserResponse.UserItem>>
    ){
        Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                gitUserPagingSource.keyword = keyword
                gitUserPagingSource
            }
        ).flowable
            .cachedIn(scope)
            .subscribe(callback::postValue)
            .let { return@let disposable::add }
    }

}