package com.expenseloom.personal.finance.manager.calenderTask.uiLayer

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtaskmayank.domainLayer.MainViewModel
import com.example.androidtaskmayank.uiLayer.customDialogs.AddTaskDialog
import com.expenseloom.personal.finance.manager.calenderTask.uiLayer.rvAdapters.DayTaskRVAdapter
import com.example.androidtaskmayank.uiLayer.rvAdapters.DateRVAdapter
import com.example.social_life.baseClasses.BaseActivity
import com.expenseloom.personal.finance.manager.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.Month


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val vm: MainViewModel by viewModels()

    private val dateAdapter by lazy { DateRVAdapter(::selectDate) }
    private val dayTaskAdapter by lazy { DayTaskRVAdapter(::performLongPressTask) }

    override fun initView() {
        initDateRV()
        initAllDayTaskRV()

        lifecycleScope.launch {
            vm.currentShowingMonth.collect {
                Log.d("MrSingh", " Selection Date : ${vm.extractSelectionDate()} ")
                dateAdapter.updateMonth(
                    vm.firstDayInMonth(),
                    vm.daysInMonth(),
                    vm.extractSelectionDate()
                )
                binding.tvMonthYear.text = "${Month.of(it.second)}, ${it.first}"
            }
        }
        lifecycleScope.launch {
            vm.toastMessage.collect {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launch {
            vm.todayTaskList.collect {
                Log.i("MrSingh", "todayTaskList:  ${it.size} ")
                dayTaskAdapter.setItems(it)
            }
        }

        with(binding) {
            ivPreviousMonth.setOnClickListener {
                vm.clickForPreviousMonth()
            }

            ivNextMonth.setOnClickListener {
                vm.clickForNextMonth()
            }

            tvShowAllTasks.setOnClickListener {
                startActivity(Intent(this@MainActivity, AllTasksActivity::class.java))
            }

            btnAddNewTask.setOnClickListener {
                if (vm.isSelectedDateCurrentDateFutureDate()) {
                    AddTaskDialog(this@MainActivity, vm.selectedDay) { title, desc ->
                        vm.addTask(title, desc)
                    }.show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "You can't add event in past date",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    private fun selectDate(date: Int) {
        vm.selectedDay = vm.dateInMillie(date)
        vm.getSelectedDayTask()
    }

    private fun performLongPressTask(taskID: Int) {
        AlertDialog.Builder(this)
            .setTitle("Do you want to delete the task!")
            .setPositiveButton("Yes") { dialog, _ ->
                vm.deleteTask(taskID)
                dialog.dismiss()
            }.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun initDateRV() {
        binding.rvCalendarDates.layoutManager = GridLayoutManager(this, 7)
        binding.rvCalendarDates.adapter = dateAdapter
    }

    private fun initAllDayTaskRV() {
        binding.rvDailyTasks.layoutManager = LinearLayoutManager(this)
        binding.rvDailyTasks.adapter = dayTaskAdapter
    }

}
