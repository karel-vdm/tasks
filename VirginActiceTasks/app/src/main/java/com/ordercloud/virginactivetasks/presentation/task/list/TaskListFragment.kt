package com.ordercloud.virginactivetasks.presentation.task.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ordercloud.virginactivetasks.R
import com.ordercloud.virginactivetasks.databinding.FragmentTaskListBinding
import com.ordercloud.virginactivetasks.domain.model.Task
import com.ordercloud.virginactivetasks.presentation.model.TaskModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskListFragment : Fragment() {

    companion object {
        private const val TAG = "TaskListFragment"
    }

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TaskListViewModel by viewModel()

    private var adapter: TaskAdapter? = null

    init {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is TaskViewState.Loading -> showLoading()
                        is TaskViewState.Success -> updateTaskList(uiState.tasks)
                        is TaskViewState.Error -> showError(uiState.errorMessage)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
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
        setCreateTaskButton()
        setTaskList()
    }

    private fun setCreateTaskButton() {
        binding.createTaskButton.setOnClickListener {
            navigateToCreateTaskFragment()
        }
    }

    private fun setTaskList() {
        adapter = TaskAdapter { task ->

        }
        binding.taskList.layoutManager = LinearLayoutManager(requireContext())
        binding.taskList.adapter = adapter
    }

    private fun showError(errorMessage: String) {

    }

    private fun updateTaskList(tasks: List<TaskModel>) {
        adapter?.submitList(tasks)
    }

    private fun showLoading() {

    }

    private fun navigateToCreateTaskFragment() {
        findNavController().navigate(R.id.create_task_fragment)
    }

}