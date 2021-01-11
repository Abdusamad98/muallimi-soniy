package com.example.android.navigation.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.android.navigation.R
import com.example.android.navigation.models.LettersData
import com.example.android.navigation.utils.getLettersData

internal class SearchAdapter ( public val context: Context) :
        RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {
    private var listener: ((Int) -> Unit)? = null
    val letters = getLettersData(context)
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var letter_lotin_search: TextView = view.findViewById(R.id.letter_lotin_search)
        var letter_search: TextView = view.findViewById(R.id.letter_search)
        var letter_pronouns_search:LinearLayout = view.findViewById(R.id.letter_pronouns_search)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_view_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val letter = letters[position]
        holder.letter_lotin_search.text = letter.letterLotin
        holder.letter_search.text = letter.letter


        holder.letter_pronouns_search.setOnClickListener {
            listener?.invoke(position)
        }

    }
    override fun getItemCount(): Int {
        return letters.size
    }


    fun setOnClickedListener(f: ((Int) -> Unit)?) {
        listener = f
    }
}