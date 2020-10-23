package com.mjjang.cheonghyunbusschedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mjjang.cheonghyunbusschedule.databinding.ListItemScheduleBinding

class ScheduleAdapter: ListAdapter<String, RecyclerView.ViewHolder>(ScheduleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ScheduleHolder(ListItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val time = getItem(position)
        (holder as ScheduleHolder).bind(time)
    }

    class ScheduleHolder(private val binding: ListItemScheduleBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.apply {
                time = item
                executePendingBindings()
            }
        }
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