package com.kzdev.projetocrud.data.db

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kzdev.projetocrud.data.db.dao.SubscriberDAO
import com.kzdev.projetocrud.data.db.entity.SubscriberEntity

@Database(entities = [SubscriberEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract val subscriberDAO: SubscriberDAO


    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null
        fun getInstance(constext: Context): AppDataBase {
            synchronized(this) {
                var instance: AppDataBase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        constext, AppDataBase::class.java, "app_database"
                    ).build()
                }

                return instance
            }
        }
    }
}

