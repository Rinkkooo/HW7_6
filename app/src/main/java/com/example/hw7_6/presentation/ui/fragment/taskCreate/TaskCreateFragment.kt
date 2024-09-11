package com.example.hw7_6.presentation.ui.fragment.taskCreate

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.hw7_6.databinding.FragmentTaskCreateBinding
import com.example.hw7_6.presentation.models.TaskEntityUI
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class TaskCreateFragment : Fragment() {

    private val binding by lazy {
        FragmentTaskCreateBinding.inflate(layoutInflater)
    }

    private val viewModel: TaskCreateViewModel by viewModel()

    private var selectedTime: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBtnCreateListener()
        setupTimePicker()
    }

    private fun setupTimePicker() {
        binding.btnPickTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
                val pickedTime = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, hourOfDay)
                    set(Calendar.MINUTE, minute)
                }
                selectedTime = (pickedTime.timeInMillis / 1000).toInt()

                binding.tvSelectedTime.text = String.format("%02d:%02d", hourOfDay, minute)
            }, hour, minute, true).show()
        }
    }


    private fun setupBtnCreateListener() {
        binding.btnCreate.setOnClickListener {
            val taskName = binding.etDesc.text.toString()
            viewModel.insertTask(
                TaskEntityUI(
                    taskId = 1,
                    taskName = taskName,
                    time = selectedTime
                )
            )

            setFragmentResult("task_request", Bundle().apply {
                putString("new_task_name", taskName)
                putInt("new_task_time", selectedTime)
            })

            findNavController().popBackStack()
        }
    }
}