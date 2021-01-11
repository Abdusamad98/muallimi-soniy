package com.example.android.navigation.screens.tajvid

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.navigation.R
import com.example.android.navigation.ViewSurahActivity
import com.example.android.navigation.adapters.LettersAdapter
import com.example.android.navigation.adapters.SuralarListAdapter
import com.example.android.navigation.databinding.SurahListFragmentBinding
import com.example.android.navigation.models.LettersData
import com.example.android.navigation.models.SurahData
import com.example.android.navigation.utils.getLettersData
import com.example.android.navigation.utils.getSurahsData

class Lesson19Fragment: Fragment() {

    internal lateinit var suralarListAdapter: SuralarListAdapter
    lateinit var surahData: ArrayList<SurahData>
    lateinit var binding: SurahListFragmentBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.surah_list_fragment, container, false)

        surahData = ArrayList<SurahData>()
        //get Data to list
        surahData = getSurahsData(context!!)
        suralarListAdapter= SuralarListAdapter(surahData,context!!)
        binding.recyclerSurah.adapter = suralarListAdapter
        binding.recyclerSurah.layoutManager = LinearLayoutManager(context)

        suralarListAdapter.setOnClickedListener {position->
            //Toast.makeText(context!!, ""+position, Toast.LENGTH_SHORT).show()
            var intent  = Intent (context,ViewSurahActivity::class.java )
            intent.putExtra("pos", position)
            startActivity(intent)
        }


        return binding.root
    }
}