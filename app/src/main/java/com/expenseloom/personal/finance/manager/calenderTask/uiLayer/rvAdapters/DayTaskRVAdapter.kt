package com.expenseloom.personal.finance.manager.calenderTask.uiLayer.rvAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.androidtaskmayank.models.Task
import com.example.androidtaskmayank.utils.CalenderUtils.Companion.formatTimeMillisToString
import com.expenseloom.personal.finance.manager.baseClasses.BaseRVAdapter
import com.expenseloom.personal.finance.manager.databinding.LayoutDailyTaskBinding


class DayTaskRVAdapter(
    private val onLongPressItem: (taskId: Int) -> Unit
) : BaseRVAdapter<Task, LayoutDailyTaskBinding>(LayoutDailyTaskBinding::inflate) {

    override fun bind(binding: LayoutDailyTaskBinding,  position: Int, item: Task) {
        with(binding) {
            val taskDetail = item.taskDetail
            tvTitle.text = taskDetail.title
            tvShowDescription.text = taskDetail.description
            try {
                tvDate.text = formatTimeMillisToString(taskDetail.date, "dd/MM/yyyy")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            root.setOnLongClickListener {
                onLongPressItem(item.taskId)
                true
            }
        }
    }

}
