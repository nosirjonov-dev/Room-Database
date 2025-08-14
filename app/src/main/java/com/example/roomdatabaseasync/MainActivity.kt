package com.example.roomdatabaseasync

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdatabaseasync.Async.AddTodoAsync
import com.example.roomdatabaseasync.Async.AddTodoCallBack
import com.example.roomdatabaseasync.Async.GetAllTodoCallBack
import com.example.roomdatabaseasync.Async.GetTodoList
import com.example.roomdatabaseasync.Database.Todo
import com.example.roomdatabaseasync.Database.TodoDatabase
import com.example.roomdatabaseasync.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var database: TodoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        database = TodoDatabase.getInstance(this)
        val insertTodo = Todo(null,"Async Todo","","","New", "8/13/2025")
        AddTodoAsync(database.todoDao(), object : AddTodoCallBack {
            override fun onControlProgressBar() {
                binding.progressBar.visibility = View.VISIBLE
            }

            override fun onFinish() {
                binding.progressBar.visibility = View.INVISIBLE
                GetTodoList(database.todoDao(), object : GetAllTodoCallBack {
                    override fun onAllListGet(list: List<Todo>?) {
                        Log.d("TAG", "onAllListGet: ${list?.joinToString() }}")
                    }

                })
            }

        }).execute(insertTodo)
    }

}
