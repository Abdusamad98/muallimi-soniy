package com.example.android.navigation
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.example.android.navigation.utils.currentSeconds
import com.example.android.navigation.utils.getSurahsData
import com.example.android.navigation.utils.seconds

@Suppress("DEPRECATION")
class ViewSurahActivity : AppCompatActivity() {

    lateinit var mPlayer_1: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    var madPlayed_1 = false

    lateinit var seekbar1: SeekBar
    lateinit var image3: ImageView


    lateinit var image1: ImageView
    lateinit var image2: ImageView
    lateinit var text_name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_surah)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        image1 = findViewById(R.id.img_1)
        image2 = findViewById(R.id.img_2)
        text_name = findViewById(R.id.sura_name)

        seekbar1 = findViewById(R.id.seekbar_1_play)
        image3 = findViewById(R.id.image1_play)


        val pos = intent.getIntExtra("pos", 0)
        val data = getSurahsData(this)
        val element = data[pos]
        setTitle(element.name+" surasi")
        if (element.imgUrl_2 == 0) {
            text_name.text = element.name
            image1.setBackgroundResource(element.imgUrl_1)
        } else {
            text_name.text = element.name
            image1.setBackgroundResource(element.imgUrl_1)
            image2.setBackgroundResource(element.imgUrl_2)
        }

        mPlayer_1 = MediaPlayer.create(this, element.mp3)
        mPlayer_1.setOnCompletionListener {
            madPlayed_1 = false
            image3.setBackgroundResource(R.drawable.play_button_green)
        }


        image3.setOnClickListener {


            if (madPlayed_1) {
                image3.setBackgroundResource(R.drawable.play_button_green)
                mPlayer_1.pause()
                madPlayed_1 = false
            } else {
                setSickBar_1()
                mPlayer_1.start()
                image3.setBackgroundResource(R.drawable.pause_button_green)
                madPlayed_1 = true
            }

        }


    }

    fun setSickBar_1() {

        seekbar1.max = mPlayer_1.seconds
        runnable = Runnable {
            seekbar1.progress = mPlayer_1.currentSeconds
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        seekbar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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
            image3.setBackgroundResource(R.drawable.play_button_green)
            mPlayer_1.pause()
            madPlayed_1 = false

        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}