package com.ujangwahyu.app.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.ujangwahyu.app.data.model.UserResponse
import com.ujangwahyu.app.data.network.ApiService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GitUserPagingSource @Inject constructor(
    private val apiService: ApiService,
): RxPagingSource<Int, UserResponse.UserItem>() {

    lateinit var keyword: String

    override fun getRefreshKey(state: PagingState<Int, UserResponse.UserItem>): Int? {
        return state.anchorPosition?.let { state.closestItemToPosition(it)?.id }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, UserResponse.UserItem>> {
        val page = params.key ?: 1
        return apiService.searchGitUsers(keyword, page)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: UserResponse, page: Int): LoadResult<Int, UserResponse.UserItem> {
        return LoadResult.Page(
            data = data.items,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == data.totalCount?.toInt()) null else page + 1
        )
    }
}