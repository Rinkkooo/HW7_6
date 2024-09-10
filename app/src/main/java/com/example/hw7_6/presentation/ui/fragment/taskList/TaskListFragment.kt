package com.example.hw7_6.presentation.ui.fragment.taskList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hw7_6.R
import com.example.hw7_6.databinding.FragmentTaskListBinding
import com.example.hw7_6.presentation.models.toUI
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskListFragment : Fragment() {

    private val binding by lazy {
        FragmentTaskListBinding.inflate(layoutInflater)
    }

    private val viewModel: TaskListViewModel by viewModel()

    private val taskListAdapter = TaskListAdapter()

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
    }

    private fun initFetchTask() {
        viewLifecycleOwner.lifecycleScope.launch {
            taskListAdapter.submitList(viewModel.fetchTasks().map { it.toUI() })
        }
    }

    private fun setupFabClickListener() {
        binding.btnCreateTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_taskCreateFragment)
        }
    }

}