package com.kzdev.projetocrud.data.db

import android.content.Context
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

        fun getInstance(context: Context): AppDataBase {
            synchronized(this) {
                var instance: AppDataBase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        "app_database"
                    ).build()
                }
                return instance
            }
        }
    }
}