package com.example.roomdatabaseasync

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.roomdatabaseasync.Database.Todo
import com.example.roomdatabaseasync.Database.TodoDatabase
import com.example.roomdatabaseasync.databinding.ActivityAddTodoBinding
import kotlinx.coroutines.launch

class AddTodoActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddTodoBinding.inflate(layoutInflater) }
    private lateinit var database : TodoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        database = TodoDatabase.getInstance(this)

        binding.floatingActionButton.setOnClickListener {
            lifecycleScope.launch {
                val title = binding.edtTitle.text.toString()
                val description = binding.edtDescription.text.toString()
                val date = binding.chooseDateButton.text.toString()
                database.todoDao().insert(Todo(null, title, description, date,"","", false))
                finish()
            }
        }
    }
}