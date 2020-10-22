package com.mjjang.cheonghyunbusschedule.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mjjang.cheonghyunbusschedule.data.BusSchedule
import com.mjjang.cheonghyunbusschedule.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {

    private val args: ScheduleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentScheduleBinding.inflate(inflater, container, false)



        return binding.root
    }

    private fun getFromToFullString(strStation: String): String {
        return when (strStation) {
            "ch" -> "청현마을"
            "gi" -> "기흥구청"
            "yo" -> "영통홈플러스"
            "ju" -> "죽전역"
            "he" -> "흥덕E마트"
            "se" -> "서천고교"
            else -> "empty"
        }
    }

    private fun getBusSchedule(bus: String, from: String, to: String, isWeekday: Boolean) : Array<String> {
        if (bus == "18" && from == "ch" && to == "gi" && isWeekday) {
            return BusSchedule.bus_18_wd_ch_to_gi
        } else if (bus == "18" && from == "ch" && to == "gi" && !isWeekday) {
            return BusSchedule.bus_18_we_ch_to_gi
        } else if (bus == "18" && from == "gi" && to == "ch" && isWeekday) {
            return BusSchedule.bus_18_wd_gi_to_ch
        } else if (bus == "18" && from == "gi" && to == "ch" && !isWeekday) {
            return BusSchedule.bus_18_we_gi_to_ch
        } else if (bus == "18" && from == "ch" && to == "yo" && isWeekday) {
            return BusSchedule.bus_18_wd_ch_to_yo
        } else if (bus == "18" && from == "ch" && to == "yo" && !isWeekday) {
            return BusSchedule.bus_18_we_ch_to_yo
        } else if (bus == "18" && from == "yo" && to == "ch" && isWeekday) {
            return BusSchedule.bus_18_wd_yo_to_ch
        } else if (bus == "18" && from == "yo" && to == "ch" && !isWeekday) {
            return BusSchedule.bus_18_we_yo_to_ch
        } else if (bus == "56" && from == "ch" && to == "ju" && isWeekday) {
            return BusSchedule.bus_56_wd_ch_to_ju
        } else if (bus == "56" && from == "ch" && to == "ju" && !isWeekday) {
            return BusSchedule.bus_56_we_ch_to_ju
        } else if (bus == "56" && from == "ju" && to == "ch" && isWeekday) {
            return BusSchedule.bus_56_wd_ju_to_ch
        } else if (bus == "56" && from == "ju" && to == "ch" && !isWeekday) {
            return BusSchedule.bus_56_we_ju_to_ch
        } else if (bus == "28-1" && from == "ch" && to == "gi" && isWeekday) {
            return BusSchedule.bus_28_1_wd_ch_to_gi
        } else if (bus == "28-1" && from == "ch" && to == "gi" && !isWeekday) {
            return BusSchedule.bus_28_1_we_ch_to_gi
        } else if (bus == "28-1" && from == "gi" && to == "ch" && isWeekday) {
            return BusSchedule.bus_28_1_wd_gi_to_ch
        } else if (bus == "28-1" && from == "gi" && to == "ch" && !isWeekday) {
            return BusSchedule.bus_28_1_we_gi_to_ch
        } else if (bus == "28-1" && from == "ch" && to == "he" && isWeekday) {
            return BusSchedule.bus_28_1_wd_ch_to_he
        } else if (bus == "28-1" && from == "ch" && to == "he" && !isWeekday) {
            return BusSchedule.bus_28_1_we_ch_to_he
        } else if (bus == "28-1" && from == "he" && to == "ch" && isWeekday) {
            return BusSchedule.bus_28_1_wd_he_to_ch
        } else if (bus == "28-1" && from == "he" && to == "ch" && !isWeekday) {
            return BusSchedule.bus_28_1_we_he_to_ch
        } else if (bus == "28-3" && from == "ch" && to == "se" && isWeekday) {
            return BusSchedule.bus_28_3_wd_ch_to_se
        } else if (bus == "28-3" && from == "ch" && to == "se" && !isWeekday) {
            return BusSchedule.bus_28_3_we_ch_to_se
        } else if (bus == "28-3" && from == "se" && to == "ch" && isWeekday) {
            return BusSchedule.bus_28_3_wd_se_to_ch
        } else if (bus == "28-3" && from == "se" && to == "ch" && !isWeekday) {
            return BusSchedule.bus_28_3_we_se_to_ch
        } else if (bus == "28-3" && from == "ch" && to == "he" && isWeekday) {
            return BusSchedule.bus_28_3_wd_ch_to_he
        } else if (bus == "28-3" && from == "ch" && to == "he" && !isWeekday) {
            return BusSchedule.bus_28_3_we_ch_to_he
        } else if (bus == "28-3" && from == "he" && to == "ch" && isWeekday) {
            return BusSchedule.bus_28_3_wd_he_to_ch
        } else if (bus == "28-3" && from == "he" && to == "ch" && !isWeekday) {
            return BusSchedule.bus_28_3_we_he_to_ch
        }
        return arrayOf()
    }
}