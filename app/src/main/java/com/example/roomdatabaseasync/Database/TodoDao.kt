package com.example.roomdatabaseasync.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {

    @Insert(onConflict = REPLACE)
    fun insert(todo: Todo)

    @Insert
    fun insertAll(todoList: List<Todo>)

    @Delete
    fun delete(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Query("select * from todo limit 10")
    fun getAllTodo():List<Todo>

    @Query("select * from todo where id = :id")
    fun getTodoById(id:Int):Todo?
}