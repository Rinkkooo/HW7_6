package com.example.hw7_6.presentation.ui.fragment.taskList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.hw7_6.R
import com.example.hw7_6.databinding.FragmentTaskListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskListFragment : Fragment() {

    private val binding by lazy {
        FragmentTaskListBinding.inflate(layoutInflater)
    }

    private val viewModel: TaskListViewModel by viewModel()

    private val taskListAdapter = TaskListAdapter {
        findNavController().navigate(R.id.action_taskListFragment_to_taskDetailFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTasks.adapter = taskListAdapter
        setupFabClickListener()
        initFetchTask()
        setupSwipeToDelete()
        viewModel.fetchTasks()

        binding.sortedList.setOnClickListener {
            initFetchFilteredTask()
        }
        binding.sortedListByTime.setOnClickListener {
            initFetchAndSortTask()
        }
    }

    private fun initFetchFilteredTask() {

        val filterDate = System.currentTimeMillis() - 86400000

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchFilteredTasks { task ->
                task.time > filterDate
            }.collectLatest { filteredTask ->
                taskListAdapter.submitList(filteredTask)
            }
        }
    }

    private fun initFetchAndSortTask() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.taskFlow.collectLatest { tasks ->
                val sortedTasks = viewModel.sortTasksByTime(tasks)
                taskListAdapter.submitList(sortedTasks)
            }
        }
    }

    private fun initFetchTask() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.taskFlow.collectLatest { newTasks ->
                val updateList = taskListAdapter.currentList + newTasks
                taskListAdapter.submitList(updateList)
            }
        }
    }


    private fun setupFabClickListener() {
        binding.btnCreateTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_taskCreateFragment)
        }
    }


    private fun setupSwipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val taskToDelete = taskListAdapter.currentList[position]
                viewModel.deleteTask(taskToDelete.taskId)
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.rvTasks)
    }

}