package com.example.hw7_6.presentation.ui.fragment.taskList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw7_6.databinding.ItemTaskBinding
import com.example.hw7_6.presentation.models.TaskEntityUI

class TaskListAdapter : ListAdapter<TaskEntityUI, TaskListAdapter.ViewHolder>(TaskDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(taskEntityUI: TaskEntityUI) {
            binding.tvTaskName.text = taskEntityUI.taskName
            binding.tvTaskDesc.text = taskEntityUI.taskDesc
            binding.tvTaskTime.text = taskEntityUI.time.toString()

        }

        private fun formatTime(timeInMillis: Long): String {
            val calendar = java.util.Calendar.getInstance().apply {
                setTimeInMillis(timeInMillis)
            }
            val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
            val minute = calendar.get(java.util.Calendar.MINUTE)
            return String.format("%02d:%02d", hour, minute)
        }
    }
}

object TaskDiffUtil : DiffUtil.ItemCallback<TaskEntityUI>(

) {
    override fun areItemsTheSame(oldItem: TaskEntityUI, newItem: TaskEntityUI): Boolean {
        return oldItem.taskId == newItem.taskId
    }

    override fun areContentsTheSame(oldItem: TaskEntityUI, newItem: TaskEntityUI): Boolean {
        return oldItem == newItem
    }
}