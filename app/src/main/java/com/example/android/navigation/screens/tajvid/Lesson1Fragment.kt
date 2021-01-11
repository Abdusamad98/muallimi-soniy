package com.example.android.navigation.screens.tajvid

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.navigation.R
import com.example.android.navigation.databinding.Lesson1FragmentBinding
import com.example.android.navigation.utils.currentSeconds
import com.example.android.navigation.utils.seconds

@Suppress("DEPRECATION")
class Lesson1Fragment : Fragment() {

    lateinit var mPlayer_1: MediaPlayer
    lateinit var mPlayer_2: MediaPlayer

    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()

    lateinit var binding: Lesson1FragmentBinding
    var madPlayed_1 = false
    var madPlayed_2 = false

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.lesson1_fragment, container, false)

        val spannable = SpannableString("'Mad' so'zi lug'atda cho'zish, uzaytirish ma'nolarini anglatadi. Mad harflari uchta: ا 'alif', و 'vav', ي 'ya'.")
        spannable.setSpan(
                RelativeSizeSpan(2f),
                spannable.indexOf("ا"), // start
                spannable.indexOf("ا") + 1, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
                RelativeSizeSpan(2f),
                spannable.indexOf("و"), // start
                spannable.indexOf("و") + 1, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
                RelativeSizeSpan(2f),
                spannable.indexOf("ي"), // start
                spannable.indexOf("ي") + 1, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        binding.text1.text = spannable
        val array = context!!.resources.getStringArray(R.array.madlar)
        val font = Typeface.createFromAsset(context!!.assets, "quran.TTF")
        binding.text2.typeface = font
        binding.text3.typeface = font
        var s1 = ""
        var s2 = ""
        for (x in array.indices) {
            if (x % 2 == 0) {
                s1 += array[x] + "\n"
            } else s2 += array[x] + "\n"
        }
        binding.text3.text = s1
        binding.text2.text = s2


        //Player 1
        mPlayer_1 = MediaPlayer.create(context, R.raw.madli_01)
        mPlayer_1.setOnCompletionListener {
            madPlayed_1 = false
            binding.image1.setBackgroundResource(R.drawable.play_button_green)
        }
        binding.image1.setOnClickListener {

            if(mPlayer_2.isPlaying){
                mPlayer_2.pause()
                binding.image2.setBackgroundResource(R.drawable.play_button_green)
                madPlayed_2 = false
            }

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

        //Player 2
        mPlayer_2 = MediaPlayer.create(context, R.raw.madli_02)
        mPlayer_2.setOnCompletionListener {
            madPlayed_2 = false
            binding.image2.setBackgroundResource(R.drawable.play_button_green)
        }
        binding.image2.setOnClickListener {
            if(mPlayer_1.isPlaying){
                binding.image1.setBackgroundResource(R.drawable.play_button_green)
                mPlayer_1.pause()
                madPlayed_1 = false
            }
            if (madPlayed_2) {
                binding.image2.setBackgroundResource(R.drawable.play_button_green)
                mPlayer_2.pause()
                madPlayed_2 = false
            } else {
                setSickBar_2()
                mPlayer_2.start()
                binding.image2.setBackgroundResource(R.drawable.pause_button_green)
                madPlayed_2 = true
            }


        }



        binding.text5.typeface = font
        binding.text5.text = context!!.resources.getString(R.string.madlar_2)


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



    fun setSickBar_2(){

        binding.seekbar2.max = mPlayer_2.seconds
        runnable = Runnable {
            binding.seekbar2.progress = mPlayer_2.currentSeconds
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        binding.seekbar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    mPlayer_2.seekTo(i * 1000)
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
        if (mPlayer_2.isPlaying()) {
            mPlayer_2.stop()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mPlayer_1.isPlaying()) {
            binding.image1.setBackgroundResource(R.drawable.play_button_green)
            mPlayer_1.pause()
            madPlayed_1 = false

        }
        if (mPlayer_2.isPlaying()) {
            binding.image2.setBackgroundResource(R.drawable.play_button_green)
            mPlayer_2.pause()
            madPlayed_2 = false
        }

    }
}