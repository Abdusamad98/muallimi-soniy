package com.example.android.navigation.screens

import android.media.MediaPlayer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.android.navigation.R
import com.example.android.navigation.adapters.LettersAdapter
import com.example.android.navigation.databinding.FragmentLettersBinding
import com.example.android.navigation.dialogs.ArabicPronounceDialog
import com.example.android.navigation.dialogs.SearchDialog
import com.example.android.navigation.models.LettersData
import com.example.android.navigation.utils.currentSeconds
import com.example.android.navigation.utils.getLettersData
import com.example.android.navigation.utils.seconds

class LettersFragment : Fragment() {


    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()


    var mPlayer: MediaPlayer? = null
    lateinit var binding: FragmentLettersBinding
    var currentPosition: Int = 0
    var isPlayed: Boolean = false
    lateinit var gridLayoutManager: GridLayoutManager
    internal lateinit var lettersAdapter: LettersAdapter
    lateinit var lettersData: ArrayList<LettersData>


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentLettersBinding>(
                inflater, R.layout.fragment_letters, container, false)
        setHasOptionsMenu(true)

        //ArrayList instance
        lettersData = ArrayList<LettersData>()
        //get Data to list
        lettersData = getLettersData(context!!)
        binding.letter.text = lettersData[currentPosition].letter
        binding.letterLotin.text = lettersData[currentPosition].letterLotin


        //Recycler setting
        gridLayoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        lettersAdapter = LettersAdapter(lettersData, context!!)
        binding.recyclerWords.adapter = lettersAdapter
        gridLayoutManager.reverseLayout = true
        binding.recyclerWords.layoutManager = gridLayoutManager

        //Scrol qilganda aynan bir item ni hold qilish uchun
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerWords)
        binding.recyclerWords.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val centerView = snapHelper.findSnapView(gridLayoutManager)
                val pos = gridLayoutManager.getPosition(centerView!!)

                if (newState == RecyclerView.SCROLL_STATE_IDLE || (pos == 0 && newState == RecyclerView.SCROLL_STATE_DRAGGING)) {

                    binding.letter.text = lettersData[pos].letter
                    binding.letterLotin.text = lettersData[pos].letterLotin
                    pauseMedia()
                    mPlayer = MediaPlayer.create(context, lettersData[pos].letterMp3)
                    currentPosition = pos
                    setSickBar()//3


                }
            }

        })


        mPlayer = MediaPlayer.create(context, lettersData[currentPosition].letterMp3)


        setClicks()

        setSickBar()//1

        return binding.root
    }


    fun setClicks() {

        binding.playButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (isPlayed) {
                    pauseMedia()
                } else {
                    playMedia()
                    mPlayer!!.setOnCompletionListener {
                        Toast.makeText(context, "Tugadi!", Toast.LENGTH_SHORT).show()
                        pauseMedia()
                    }

                }
            }
        })

        binding.replayButton.setOnClickListener {
            pauseMedia()
            mPlayer = MediaPlayer.create(context, lettersData[currentPosition].letterMp3)
            setSickBar()//3
            playMedia()

            mPlayer!!.setOnCompletionListener {
                Toast.makeText(context, "Tugadi", Toast.LENGTH_SHORT).show()
                pauseMedia()
            }
        }

        binding.letterPronouns.setOnClickListener {
            if (currentPosition != 0) {
                val dialog = ArabicPronounceDialog(context!!, currentPosition)
                dialog.show()
            }
        }


        binding.leftClick.setOnClickListener {
            if (lettersData.size - 1 == currentPosition) {
                Toast.makeText(this.requireActivity(), "Bu so'nggi sahifa!", Toast.LENGTH_SHORT).show()
            } else {
                currentPosition = currentPosition + 1
                gridLayoutManager.scrollToPosition(currentPosition)

                binding.letter.text = lettersData[currentPosition].letter
                binding.letterLotin.text = lettersData[currentPosition].letterLotin

                pauseMedia()
                mPlayer = MediaPlayer.create(context, lettersData[currentPosition].letterMp3)
                setSickBar()//4

            }

        }



        binding.rightClick.setOnClickListener {
            if (currentPosition == 0) {
                Toast.makeText(this.requireActivity(), "Bu birinchi sahifa!", Toast.LENGTH_SHORT).show()
            } else {
                currentPosition = currentPosition - 1
                gridLayoutManager.scrollToPosition(currentPosition)

                binding.letter.text = lettersData[currentPosition].letter
                binding.letterLotin.text = lettersData[currentPosition].letterLotin

                pauseMedia()
                mPlayer = MediaPlayer.create(context, lettersData[currentPosition].letterMp3)
                setSickBar()//5
            }

        }
    }

    fun playMedia() {
        isPlayed = true
        binding.playButton.setBackgroundResource(R.drawable.pause_button)
        mPlayer!!.start()
    }

    fun pauseMedia() {
        mPlayer!!.pause()
        binding.playButton.setBackgroundResource(R.drawable.play_button)
        isPlayed = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPlayer!!.isPlaying()) {
            mPlayer!!.stop()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> searchLetter()
        }
        return super.onOptionsItemSelected(item)

    }

    fun searchLetter() {
       pauseMedia()

        val dialogSearch = SearchDialog(context!!)
        dialogSearch.show()
        dialogSearch.setOnSubmitListener { selectedPosition ->
            if (selectedPosition != currentPosition) {

                gridLayoutManager.scrollToPosition(selectedPosition)
                currentPosition = selectedPosition

                binding.letter.text = lettersData[currentPosition].letter
                binding.letterLotin.text = lettersData[currentPosition].letterLotin

                mPlayer = MediaPlayer.create(context, lettersData[currentPosition].letterMp3)
                setSickBar()///2

                Toast.makeText(context!!, ""+lettersData[selectedPosition].letter, Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun setSickBar(){

        binding.seekbar.max = mPlayer!!.seconds
        runnable = Runnable {
            binding.seekbar.progress = mPlayer!!.currentSeconds
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)


        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    mPlayer!!.seekTo(i * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })


    }


}
