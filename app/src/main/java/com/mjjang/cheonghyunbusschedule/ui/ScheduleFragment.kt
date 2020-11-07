package com.mjjang.cheonghyunbusschedule.ui

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import com.mjjang.cheonghyunbusschedule.adapter.ScheduleAdapter
import com.mjjang.cheonghyunbusschedule.data.BusSchedule
import com.mjjang.cheonghyunbusschedule.databinding.FragmentScheduleBinding
import com.mjjang.cheonghyunbusschedule.util.TimeUtil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class ScheduleFragment : Fragment() {

    private val LAST_BUS_IS_OVER = 99999

    private val args: ScheduleFragmentArgs by navArgs()

    val schedule: MutableLiveData<List<String>> = MutableLiveData()

    val mTime: MutableLiveData<String> = MutableLiveData()

    lateinit var binding: FragmentScheduleBinding

    private lateinit var busSchedule: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)

        val adapter = ScheduleAdapter()
        binding.recyclerSchedule.adapter = adapter
        schedule.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        mTime.observe(viewLifecycleOwner) {
            binding.textTime.text = it
            val timeGap = calcArriveTime(busSchedule, it)
            setArriveTime(timeGap)
            val adapter = binding.recyclerSchedule.adapter
            if (adapter is ScheduleAdapter) {
                val selectPosition = adapter.getSelectPosition(it)
                adapter.setSelected(selectPosition)
                binding.recyclerSchedule.scrollToPosition(selectPosition)
            }
        }

        binding.btnChangeTime.setOnClickListener {
            val simpleDataFormat = SimpleDateFormat("HH:mm")
            val date = simpleDataFormat.parse(binding.textTime.text.toString())
            Calendar.getInstance().time = date
            TimePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Light_Dialog,
                { p0, p1, p2 ->
                    mTime.value = "$p1:$p2"
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
            ).show()
        }

        binding.textBusLine.text = "${getFromToFullString(args.from)} -> ${getFromToFullString(args.to)}"

        val currentTime = getCurrentTime()
        // 버스, 노선, 시간에 알맞은 시간표를 가져와서 adapter에 씀
        setScheduleData(args.bus, args.from, args.to, currentTime, Calendar.getInstance().get(Calendar.DAY_OF_WEEK))

        return binding.root
    }

    private fun setScheduleData(bus: String, from: String, to: String, time: String, dayOfWeek: Int) {
        busSchedule = getBusSchedule(bus, from, to, isWeekDay(dayOfWeek))

        // 28-1번은 일요일, 공휴일에는 운행하지 않음
        // 공휴일 정보를 가져오려면 처리가 필요한데... 일단은 텍스트로 표출해놓자..
        if (bus == "28-1" && dayOfWeek == Calendar.SUNDAY) {
            busSchedule = emptyArray()
        }

        mTime.value = time

        schedule.value = busSchedule.toList()
    }

    private fun setArriveTime(timeGap: Int) {
        if (timeGap == LAST_BUS_IS_OVER) {
            binding.textArrive.text = ""
            binding.textArrive.visibility = View.GONE
            binding.textArriveTail.text = "운행이 종료되었습니다"
        } else {
            binding.textArrive.text = abs(timeGap).toString() + "분"
            binding.textArrive.visibility = View.VISIBLE
            binding.textArriveTail.text = "후 도착합니다"
        }
    }

    private fun calcArriveTime(busSchedule: Array<String>, currentTime: String): Int {

        busSchedule.forEach {
            val timeGap = TimeUtil.calcTimeGap(currentTime, it)
            if (timeGap < 0) {
                return timeGap
            }
        }

        return LAST_BUS_IS_OVER
    }

    private fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        return String.format("%d:%02d", hour, min)
    }

    private fun isWeekDay(dayOfWeek: Int): Boolean {
        return !(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY)
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