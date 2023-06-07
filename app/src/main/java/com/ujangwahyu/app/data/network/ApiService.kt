package com.ujangwahyu.app.data.network

import com.ujangwahyu.app.data.model.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/users?sort=followers")
    fun searchGitUsers(
        @Query("q") query: String?,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int = 10
    ): Single<UserResponse>

}