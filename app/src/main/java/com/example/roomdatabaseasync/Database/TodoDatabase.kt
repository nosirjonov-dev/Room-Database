package com.example.roomdatabaseasync.Database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [Todo::class],
    version = 4,                 // versiya zanjiringizga moslang
    exportSchema = true,         // MUHIM
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = TodoDatabase.Migration2to3::class),
        AutoMigration(from = 3, to = 4)
    ]
)
abstract class TodoDatabase : RoomDatabase() {
    @RenameColumn(tableName = "todo", fromColumnName = "data", toColumnName = "createdAt")
    class Migration2to3 : AutoMigrationSpec

    abstract fun todoDao(): TodoDao

    companion object {
        private var INSTANCE: TodoDatabase? = null
        fun getInstance(context: Context): TodoDatabase {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                "myRoomDatabase"
            )
                // .fallbackToDestructiveMigration() // agar tarix saqlash shart bo‘lmasa
                .build()
        }
    }
}
