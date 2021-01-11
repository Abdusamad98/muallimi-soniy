package com.example.android.navigation.screens.tajvid

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
import com.example.android.navigation.databinding.Lesson9FragmentBinding
import com.example.android.navigation.utils.currentSeconds
import com.example.android.navigation.utils.seconds

class Lesson9Fragment: Fragment() {


    lateinit var mPlayer_1: MediaPlayer
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    var madPlayed_1 = false

    lateinit var binding: Lesson9FragmentBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.lesson9_fragment, container, false)



        val font = Typeface.createFromAsset(context!!.assets, "quran.TTF")

        val spannable = SpannableString("So'z oxirida kelgan zammali 'ـهُ'   ha dan oldingi harf ham zammali yoki fathali bo'lsa, 'ـهُ' - ha dan keyin bir  'و' vav qo'shib cho'zib o'qiladi" +
                ". Bu alomat Usmon Musxafida kichkina vov ko'rinishida kelgan. Bunda ham sharti shuki, undan keyin qa'tiy hamza kelmasligi kerak bo'ladi. Misollar:\n")
        spannable.setSpan(
                RelativeSizeSpan(2f),
                spannable.indexOf("ـهُ"), // start
                spannable.indexOf("ـهُ") + 2, // end
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
               90, // start
               93, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )


        binding.text1.text = spannable

        binding.text.text = context!!.resources.getString(R.string.vovi_muqaddara)
        binding.text.typeface = font
        mPlayer_1 = MediaPlayer.create(context, R.raw.vov_muqoddaro)
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