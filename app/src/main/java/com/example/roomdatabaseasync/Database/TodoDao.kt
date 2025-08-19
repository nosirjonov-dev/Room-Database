package com.example.roomdatabaseasync.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(todo: Todo)

    @Insert
    suspend fun insertAll(todoList: List<Todo>)

    @Delete
    suspend fun delete(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Query("select * from todo limit 10")
    fun getAllTodo(): Flow<List<Todo>>

    @Query("select * from todo where id = :id")
    suspend fun getTodoById(id:Int):Todo?
}