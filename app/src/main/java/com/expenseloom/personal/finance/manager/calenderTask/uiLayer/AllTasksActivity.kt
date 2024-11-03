package com.expenseloom.personal.finance.manager.calenderTask.uiLayer

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtaskmayank.domainLayer.AllTasksVM
import com.expenseloom.personal.finance.manager.calenderTask.uiLayer.rvAdapters.DayTaskRVAdapter
import com.example.social_life.baseClasses.BaseActivity
import com.expenseloom.personal.finance.manager.databinding.ActivityAllTasksBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AllTasksActivity : BaseActivity<ActivityAllTasksBinding>(ActivityAllTasksBinding::inflate) {

    private val vm: AllTasksVM by viewModels()
    private val allTasksAdapter by lazy { DayTaskRVAdapter(::performLongPressTask) }

    override fun initView() {

        lifecycleScope.launch {
            vm.taskList.collect { tasks ->
                Log.d("MrSingh", " Task Details Size : ${tasks.size} ")
                allTasksAdapter.submitList(tasks)
            }
        }

        lifecycleScope.launch {
            vm.toastMessage.collect { toastMessage ->
                Toast.makeText(this@AllTasksActivity, toastMessage, Toast.LENGTH_SHORT).show()
            }
        }

        initAllTaskRV()
    }

    private fun initAllTaskRV() {
        binding.rvDailyTasks.layoutManager = LinearLayoutManager(this)
        binding.rvDailyTasks.adapter = allTasksAdapter
    }

    private fun performLongPressTask(taskId: Int) {
        AlertDialog.Builder(this)
            .setTitle("Do you want to delete the task!")
            .setPositiveButton("Yes") { dialog, _ ->
                vm.deleteTask(taskId)
                dialog.dismiss()
            }.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }.show()

    }
}

