package com.example.hw7_6.presentation.ui.fragment.taskCreate

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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

    private var selectedTime: Long = 0

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

            TimePickerDialog(
                requireContext(),
                { _, selectedHour, selectedMinute ->
                    val selectedTimeInMillis = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, selectedHour)
                        set(Calendar.MINUTE, selectedMinute)
                    }.timeInMillis
                    selectedTime = selectedTimeInMillis
                    binding.tvSelectedTime.text = formatTime(selectedTimeInMillis)
                },
                hour,
                minute,
                true
            ).show()
        }
    }


    private fun setupBtnCreateListener() {
        binding.btnCreate.setOnClickListener {
            val taskName = binding.etDesc.text.toString()
            val taskDesc = binding.etDesc.text.toString()

            if (taskName.isNotEmpty() && taskDesc.isNotEmpty()) {
                viewModel.insertTask(
                    TaskEntityUI(
                        taskId = 1,
                        taskName = taskName,
                        taskDesc = taskDesc,
                        time = selectedTime
                    )
                )
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Заполните поле", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun formatTime(timeInMillis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return String.format("%02d:%02d", hour, minute)
    }
}