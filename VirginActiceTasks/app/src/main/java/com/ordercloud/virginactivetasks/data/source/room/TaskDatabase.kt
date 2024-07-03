package com.ordercloud.virginactivetasks.data.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ordercloud.virginactivetasks.data.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}