package com.example.roomdatabaseasync.Utils

import com.example.roomdatabaseasync.Database.Todo

interface TodoListener {
    fun checkBoxClick(todo: Todo, isChecked:Boolean)
}