package com.example.roomdatabaseasync

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseasync.Database.Todo
import com.example.roomdatabaseasync.Database.TodoDatabase
import com.example.roomdatabaseasync.Utils.TodoAdapter
import com.example.roomdatabaseasync.Utils.TodoListener
import com.example.roomdatabaseasync.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var database: TodoDatabase
    private val TAG = "TAG"
    private var counter = 0
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        todoAdapter = TodoAdapter()
        database = TodoDatabase.getInstance(this)

        binding.rv.adapter = todoAdapter
        binding.rv.layoutManager = LinearLayoutManager(this)


        lifecycleScope.launch {
            database.todoDao().getAllTodo().collectLatest {todoList->
                counter = todoList.size
                todoAdapter.submitList(todoList)
                todoAdapter.notifyDataSetChanged()
                Log.d(TAG, "onCreate: ${todoList.joinToString()}")
            }
        }

        binding.fabBtn.setOnClickListener {
            Intent(this, AddTodoActivity::class.java).apply {
                startActivity(this)
            }
        }

        todoAdapter.setListener(object : TodoListener {
            override fun checkBoxClick(todo: Todo, isChecked: Boolean) {
                lifecycleScope.launch{
                    todo.isChecked = isChecked
                    database.todoDao().update(todo)
                }
            }

        })

        binding.rv.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }

}

