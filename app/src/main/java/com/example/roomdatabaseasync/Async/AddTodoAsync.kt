package com.example.roomdatabaseasync.Async

import android.os.AsyncTask
import com.example.roomdatabaseasync.Database.Todo
import com.example.roomdatabaseasync.Database.TodoDao

class AddTodoAsync(val todoDao: TodoDao, val addTodoCallBack: AddTodoCallBack): AsyncTask<Todo, Unit, Unit>(){

    override fun onPreExecute() {
        super.onPreExecute()
        addTodoCallBack.onControlProgressBar()
    }

    override fun doInBackground(vararg todo: Todo?) {
        Thread.sleep(2000)
        todo[0]?.apply {
//            todoDao.insert(this)
        }
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        addTodoCallBack.onFinish()
    }
}