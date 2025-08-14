package com.example.roomdatabaseasync.Async

import android.os.AsyncTask
import android.util.Log
import com.example.roomdatabaseasync.Database.Todo
import com.example.roomdatabaseasync.Database.TodoDao

class GetTodoList(val todoDao: TodoDao, val getAllTodoCallBack: GetAllTodoCallBack): AsyncTask<Unit, Unit, List<Todo>>(){
    override fun doInBackground(vararg p0: Unit?): List<Todo> {
        return todoDao.getAllTodo()
    }

    override fun onPostExecute(result: List<Todo>?) {
        super.onPostExecute(result)
        getAllTodoCallBack.onAllListGet(result)
        Log.d("TAG", "onPostExecute: ${result?.joinToString()}")
    }
}