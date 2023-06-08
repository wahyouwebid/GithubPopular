package com.ujangwahyu.app.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ujangwahyu.app.data.entity.UserEntity
import io.reactivex.rxjava3.core.Completable

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUser(): PagingSource<Int, UserEntity>

    @Insert
    fun insert(data: UserEntity): Completable

    @Update
    fun update(data : UserEntity): Completable

    @Query("DELETE FROM user WHERE id = :id")
    fun delete(id: Int): Completable
}