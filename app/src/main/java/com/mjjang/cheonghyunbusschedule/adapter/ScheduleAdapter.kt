package com.mjjang.cheonghyunbusschedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mjjang.cheonghyunbusschedule.databinding.ListItemScheduleBinding
import com.mjjang.cheonghyunbusschedule.util.TimeUtil

class ScheduleAdapter: ListAdapter<String, RecyclerView.ViewHolder>(ScheduleDiffCallback()) {

    var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ScheduleHolder(ListItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val time = getItem(position)
        (holder as ScheduleHolder).bind(time, position == selectedPosition)
    }

    class ScheduleHolder(private val binding: ListItemScheduleBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String, isSelected: Boolean) {
            binding.apply {
                time = item
                executePendingBindings()

                textTime.isSelected = isSelected
            }
        }
    }

    fun getSelectPosition(time: String): Int {
        for (i in 0 until currentList.size) {
            val timeGap = TimeUtil.calcTimeGap(time, currentList[i])
            if (timeGap < 0) {
                return i
            }
        }
        return -1
    }

    fun setSelected(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }
}

class ScheduleDiffCallback: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}