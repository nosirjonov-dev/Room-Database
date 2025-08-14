package com.example.roomdatabaseasync.Async

import com.example.roomdatabaseasync.Database.Todo

interface GetAllTodoCallBack {

    fun onAllListGet(list: List<Todo>?)

}