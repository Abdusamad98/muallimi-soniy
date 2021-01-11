package com.example.android.navigation.screens.tajvid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.R
import com.example.android.navigation.databinding.TajvidHomeFragmentBinding

class TajveedHomeFragment : Fragment() {

    lateinit var binding: TajvidHomeFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.tajvid_home_fragment, container, false)

        binding.button1.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson1Fragment())
        }



        binding.button2.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson2Fragment())
        }


        binding.button3.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson3Fragment())
        }


        binding.button4.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson4Fragment())
        }


        binding.button5.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson5Fragment())
        }



        binding.button6.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson6Fragment())
        }



        binding.button6.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson6Fragment())
        }


        binding.button7.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson7Fragment())
        }



        binding.button8.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson8Fragment())
        }



        binding.button9.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson9Fragment())
        }



        binding.button10.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson10Fragment())
        }



        binding.button11.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson11Fragment())
        }


        binding.button12.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson12Fragment())
        }

        binding.button13.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson13Fragment())
        }

        binding.button14.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson14Fragment())
        }


        binding.button15.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson15Fragment())
        }


        binding.button16.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson16Fragment())
        }

        binding.button17.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson17Fragment())
        }


        binding.button18.setOnClickListener { v: View ->
            v.findNavController().navigate(TajveedHomeFragmentDirections.actionTajveedHomeFragmentToLesson18Fragment())
        }


        return binding.root
    }

}