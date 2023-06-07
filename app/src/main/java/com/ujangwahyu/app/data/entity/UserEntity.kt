package com.ujangwahyu.app.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user")
@Parcelize
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name: String?,
    val role: String?,
    val github: String?,
): Parcelable