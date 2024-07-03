package com.ordercloud.virginactivetasks.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ordercloud.virginactivetasks.data.model.TaskEntity
import com.ordercloud.virginactivetasks.databinding.ActivityMainBinding
import com.ordercloud.virginactivetasks.domain.model.Task
import com.ordercloud.virginactivetasks.presentation.task.list.TaskAdapter
import com.ordercloud.virginactivetasks.presentation.task.viewmodel.TaskViewModel
import com.ordercloud.virginactivetasks.presentation.task.viewmodel.TaskViewState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}