package com.example.hw7_6.presentation.ui.fragment.taskDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hw7_6.R
import com.example.hw7_6.databinding.FragmentTaskDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TaskDetailFragment : Fragment() {

    private val binding by lazy {
        FragmentTaskDetailBinding.inflate(layoutInflater)
    }

    private val viewModel : TaskDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskId = arguments?.getInt("taskId") ?: return
        viewModel.getTaskById(taskId).observe(viewLifecycleOwner) { task ->
            task?.let {
                binding.tvTaskName.text = it.taskName
                binding.tvTaskDesc.text = it.taskDesc
                binding.tvTime.text = formatTime(it.time)
            }
        }

    }

    private fun formatTime(time: Int): String {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = time.toLong() * 1000
        }
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

}