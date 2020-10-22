package com.mjjang.cheonghyunbusschedule.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mjjang.cheonghyunbusschedule.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnBus18ChToGi.setOnClickListener {
            navigateToSchedule(it, "18", "ch", "gi")
        }

        binding.btnBus18GiToCh.setOnClickListener {
            navigateToSchedule(it, "18", "gi", "ch")
        }

        binding.btnBus18ChToYo.setOnClickListener {
            navigateToSchedule(it, "18", "ch", "yo")
        }

        binding.btnBus18YoToCh.setOnClickListener {
            navigateToSchedule(it, "18", "yo", "ch")
        }

        binding.btnBus56ChToJu.setOnClickListener {
            navigateToSchedule(it, "56", "ch", "ju")
        }

        binding.btnBus56JuToCh.setOnClickListener {
            navigateToSchedule(it, "56", "ju", "ch")
        }

        binding.btnBus281ChToGi.setOnClickListener {
            navigateToSchedule(it, "28-1", "ch", "gi")
        }

        binding.btnBus281GiToCh.setOnClickListener {
            navigateToSchedule(it, "28-1", "gi", "ch")
        }

        binding.btnBus281ChToHe.setOnClickListener {
            navigateToSchedule(it, "28-1", "ch", "he")
        }

        binding.btnBus281HeToCh.setOnClickListener {
            navigateToSchedule(it, "28-1", "he", "ch")
        }

        binding.btnBus283ChToSe.setOnClickListener {
            navigateToSchedule(it, "28-3", "ch", "se")
        }

        binding.btnBus283SeToCh.setOnClickListener {
            navigateToSchedule(it, "28-3", "se", "ch")
        }

        binding.btnBus283ChToHe.setOnClickListener {
            navigateToSchedule(it, "28-3", "ch", "he")
        }

        binding.btnBus283HeToCh.setOnClickListener {
            navigateToSchedule(it, "28-3", "he", "ch")
        }

        return binding.root
    }

    private fun navigateToSchedule(view: View, bus: String, from: String, to: String) {
        val direction =
            HomeFragmentDirections.actionFragmentMainToFragmentSchedule(bus, from, to)
        view.findNavController().navigate(direction)
    }
}