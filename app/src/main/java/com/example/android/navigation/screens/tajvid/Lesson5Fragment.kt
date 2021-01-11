package com.example.android.navigation.screens.tajvid

import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.navigation.R
import com.example.android.navigation.databinding.Lesson5FragmentBinding
import com.example.android.navigation.utils.currentSeconds
import com.example.android.navigation.utils.seconds

class Lesson5Fragment: Fragment() {


    lateinit var mPlayer_1: MediaPlayer
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    var madPlayed_1 = false

    lateinit var binding: Lesson5FragmentBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.lesson5_fragment, container, false)



        val font = Typeface.createFromAsset(context!!.assets, "majallab.ttf")
        binding.text.text = context!!.resources.getString(R.string.alif_va_hamza)
        binding.text.typeface = font
        mPlayer_1 = MediaPlayer.create(context, R.raw.alif_va_hamza)
        mPlayer_1.setOnCompletionListener {
            madPlayed_1 = false
            binding.image1.setBackgroundResource(R.drawable.play_button_green)
        }


        binding.image1.setOnClickListener {


            if (madPlayed_1) {
                binding.image1.setBackgroundResource(R.drawable.play_button_green)
                mPlayer_1.pause()
                madPlayed_1 = false
            } else {
                setSickBar_1()
                mPlayer_1.start()
                binding.image1.setBackgroundResource(R.drawable.pause_button_green)
                madPlayed_1 = true
            }

        }




        return binding.root
    }

    fun setSickBar_1(){

        binding.seekbar1.max = mPlayer_1.seconds
        runnable = Runnable {
            binding.seekbar1.progress = mPlayer_1.currentSeconds
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        binding.seekbar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    mPlayer_1.seekTo(i * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })


    }




    override fun onDestroy() {
        super.onDestroy()
        //Player_1
        if (mPlayer_1.isPlaying()) {
            mPlayer_1.stop()
        }

    }

    override fun onStop() {
        super.onStop()
        if (mPlayer_1.isPlaying()) {
            binding.image1.setBackgroundResource(R.drawable.play_button_green)
            mPlayer_1.pause()
            madPlayed_1 = false

        }

    }

}