package com.ordercloud.virginactivetasks.presentation.task.create

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.ordercloud.virginactivetasks.data.model.TaskEntity
import com.ordercloud.virginactivetasks.databinding.FragmentCreateTaskBinding
import com.ordercloud.virginactivetasks.presentation.components.DatePicker
import com.ordercloud.virginactivetasks.presentation.model.TaskModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Locale


class CreateTaskFragment : Fragment() {

    companion object {
        private const val TAG = "CreateTaskFragment"
    }

    private var _binding: FragmentCreateTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateTaskViewModel by viewModel()

    init {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is TaskViewState.Loading -> showLoading()
                        is TaskViewState.Success -> navigateBack()
                        is TaskViewState.Error -> showError(uiState.errorMessage)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun setView() {
        setDatePicker()
        setCreateTaskButton()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setDatePicker() {
        binding.taskDueDate.editText?.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                DatePicker.showDatePicker(requireContext()) {
                    (view as EditText).setText(it)
                }
                return@setOnTouchListener true
            }
            return@setOnTouchListener false
        }
    }

    private fun setCreateTaskButton() {
        binding.createTaskButton.setOnClickListener {
            val title = binding.taskTitle.editText?.text ?: ""
            val description = binding.taskDescription.editText?.text ?: ""
            val dueDate = binding.taskDueDate.editText?.text ?: ""
            val completed = binding.isCompleted.isChecked

            val task = TaskModel(
                title = title.toString(),
                description = description.toString(),
                dueDate = dueDate.toString(),
                isCompleted = completed,
            )
            viewModel.onCreateTaskSelected(task)
        }
    }

    private fun showLoading() {
    }

    private fun showError(errorMessage: String) {
        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun navigateBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}