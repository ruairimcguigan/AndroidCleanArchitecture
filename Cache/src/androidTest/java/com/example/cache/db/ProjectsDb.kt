package com.example.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache.dao.CachedProjectsDao
import com.example.cache.dao.ConfigDao
import com.example.cache.db.ProjectConstants.DB_NAME
import com.example.cache.model.CachedProject
import com.example.cache.model.Config
import javax.inject.Inject

@Database(entities = arrayOf(CachedProject::class, Config::class), version = 1)
abstract class ProjectsDb @Inject constructor() : RoomDatabase() {

    private var DB_INSTANCE: ProjectsDb? = null
    private var lock = Any()

    fun getDbInstance(context: Context): ProjectsDb {
        if (DB_INSTANCE == null) {
            synchronized(lock) {
                DB_INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ProjectsDb::class.java, DB_NAME)
                        .build()
            }
            return DB_INSTANCE as ProjectsDb
        }
        return DB_INSTANCE as ProjectsDb
    }

    abstract fun cachedProjectsDao(): CachedProjectsDao

    abstract fun configDao(): ConfigDao
}