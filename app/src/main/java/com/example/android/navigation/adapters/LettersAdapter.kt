package com.example.android.navigation.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.android.navigation.R
import com.example.android.navigation.models.LettersData

internal class LettersAdapter(private var letters: ArrayList<LettersData>, public val context: Context) :
        RecyclerView.Adapter<LettersAdapter.MyViewHolder>() {

    private var listener: ((Int) -> Unit)? = null

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var wordstext: TextView = view.findViewById(R.id.words_text)
        var descriptionText: TextView = view.findViewById(R.id.description)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.letters_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val font = Typeface.createFromAsset(context!!.assets, "DroidNaskh-Regular.ttf");
        val letters = letters[position]
        holder.wordstext.text = letters.letterWords
        holder.wordstext.setTypeface(font)
        holder.descriptionText.text = letters.letterDescription
    }

    override fun getItemCount(): Int {
        return letters.size
    }


    fun setOnClickedListener(f: ((Int) -> Unit)?) {
        listener = f
    }
}