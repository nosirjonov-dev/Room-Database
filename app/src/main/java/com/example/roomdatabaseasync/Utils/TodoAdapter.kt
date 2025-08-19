package com.example.roomdatabaseasync.Utils


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseasync.Database.Todo
import com.example.roomdatabaseasync.databinding.ItemTodoBinding

private class TodoDifFUtil():DiffUtil.ItemCallback<Todo>(){
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }

}

class TodoAdapter:ListAdapter<Todo, TodoAdapter.TodoHolder>(TodoDifFUtil()) {

    private var listener:TodoListener? = null

    inner class TodoHolder (private val itemTodoBinding : ItemTodoBinding):RecyclerView.ViewHolder(itemTodoBinding.root){

        init {
            itemTodoBinding.checkBox.setOnCheckedChangeListener{ checkBox, isChecked ->
                if (checkBox.isPressed)
                    listener?.checkBoxClick(getItem(adapterPosition), isChecked)
            }
        }

        fun bind(todo: Todo){
            itemTodoBinding.titleTv.text = todo.title
            itemTodoBinding.descriptionTv.text = todo.description
            itemTodoBinding.createdDateTv.text = todo.createdAt
            itemTodoBinding.checkBox.isChecked = todo.isChecked
        }
    }

    fun setListener(listener: TodoListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        return TodoHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(getItem(position))
    }


}