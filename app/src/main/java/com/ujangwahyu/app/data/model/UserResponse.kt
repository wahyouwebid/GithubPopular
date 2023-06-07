package com.ujangwahyu.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UserResponse(
    @field:SerializedName("total_count")
    val totalCount: Long?,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean?,

    @field:SerializedName("items")
    val items: List<UserItem>,
) {
    @Parcelize
    data class UserItem(
        @field:SerializedName("id")
        val id: Int?,

        @field:SerializedName("login")
        val login: String?,

        @field:SerializedName("avatar_url")
        val avatarUrl: String?,

        @field:SerializedName("type")
        val type: String?,

        @field:SerializedName("name")
        val name: String?,

        @field:SerializedName("company")
        val company: String?,

        @field:SerializedName("blog")
        val blog: String?,

        @field:SerializedName("location")
        val location: String?,

        @field:SerializedName("public_repos")
        val publicRepos: String?,

        @field:SerializedName("followers")
        val followers: String?,

        @field:SerializedName("following")
        val following: String?,

        @field:SerializedName("bio")
        val bio: String?,
    ):Parcelable
}

