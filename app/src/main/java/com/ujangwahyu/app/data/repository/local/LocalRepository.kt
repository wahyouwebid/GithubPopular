package com.ujangwahyu.app.data.repository.local

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import com.ujangwahyu.app.data.database.RoomDB
import com.ujangwahyu.app.data.entity.UserEntity
import com.ujangwahyu.app.data.model.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class LocalRepository @Inject constructor(
    private val db: RoomDB,
    private val disposable: CompositeDisposable
) {

    fun getUser(
        scope: CoroutineScope,
        callback: MutableLiveData<PagingData<UserEntity>>
    ) {
        Pager(PagingConfig(10)) {
            db.userDao().getUser()
        }.flowable
            .cachedIn(scope)
            .subscribe(callback::postValue)
            .let { return@let disposable::add }
    }

    fun insertUser(data: UserEntity, onSuccess: (Boolean) -> Unit) {
        db.userDao().insert(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess.invoke(true) },
                { onSuccess.invoke(false) }
            ).let(disposable::add)
    }

    fun updateUser(data: UserEntity, onSuccess: (Boolean) -> Unit) {
        db.userDao().update(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess.invoke(true) },
                { onSuccess.invoke(false) }
            ).let(disposable::add)
    }

    fun deleteUser(id: Int, onSuccess: (Boolean) -> Unit) {
        db.userDao().delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess.invoke(true) },
                { onSuccess.invoke(false) }
            ).let(disposable::add)
    }
}