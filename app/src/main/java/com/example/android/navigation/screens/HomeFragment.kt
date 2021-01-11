package com.example.android.navigation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.R
import com.example.android.navigation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false)

        binding.btn1.setOnClickListener { v: View ->
            v.findNavController().navigate(HomeFragmentDirections.actionTitleFragmentToGameFragment())
        }

        binding.btn2.setOnClickListener { v: View ->
            v.findNavController().navigate(HomeFragmentDirections.actionTitleFragmentToTajveedHomeFragment())
        }


        binding.btn3.setOnClickListener { v: View ->
            v.findNavController().navigate(HomeFragmentDirections.actionTitleFragmentToLesson19Fragment())
        }




        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}
