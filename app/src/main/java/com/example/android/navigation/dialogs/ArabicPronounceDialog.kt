package com.example.android.navigation.dialogs

import android.content.Context
import android.widget.Button
import android.widget.VideoView
import com.example.android.navigation.R
import com.example.android.navigation.utils.getLettersData

class ArabicPronounceDialog(context: Context, currentPosition: Int) :
    BaseDialog(context, R.layout.item_dialog_video) {

    val data = getLettersData(context)
    var isPlayed = true
    var btn_play_pause: Button = view.findViewById(R.id.play_pause)
    var btn_close: Button = view.findViewById(R.id.button_close)
    var video_view: VideoView = view.findViewById(R.id.video_view)
    var path =
        "android.resource://" + context.packageName + "/" + data[currentPosition].letterPronounVideo

    init {
        view.apply {

            video_view.setVideoPath(path)
            video_view.start()


            btn_close.setOnClickListener {
                if (video_view.isActivated) {
                    video_view.stopPlayback()
                    close()
                } else {
                    close()
                }
            }

            btn_play_pause.setOnClickListener {
                if (isPlayed) {
                    video_view.pause()
                    btn_play_pause.setBackgroundResource(R.drawable.play_button_green)
                    isPlayed = false
                } else {
                    btn_play_pause.setBackgroundResource(R.drawable.pause_button_green)
                    video_view.start()
                    isPlayed = true
                }
            }
            video_view.setOnCompletionListener {
                btn_play_pause.setBackgroundResource(R.drawable.play_button_green)
                isPlayed = false
            }

        }
    }
}

