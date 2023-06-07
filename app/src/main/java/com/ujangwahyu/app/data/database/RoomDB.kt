package com.ujangwahyu.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ujangwahyu.app.BuildConfig
import com.ujangwahyu.app.data.database.dao.UserDao
import com.ujangwahyu.app.data.entity.UserEntity

@Database(
    entities = [UserEntity::class, ],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {

        @Volatile private var instance : RoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            "ujangwahyu.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }
}