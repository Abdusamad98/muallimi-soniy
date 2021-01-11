package com.example.android.navigation.adapters

import com.example.android.navigation.models.SurahData

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.android.navigation.R

internal class SuralarListAdapter(private var surahs: ArrayList<SurahData>, public val context: Context) :
        RecyclerView.Adapter<SuralarListAdapter.MyViewHolder>() {

    private var listener: ((Int) -> Unit)? = null

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.surah_name)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_surh, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sura = surahs[position]
        holder.name.text = sura.name
        holder.name.setOnClickListener {
            listener!!.invoke(position)
        }

    }

    override fun getItemCount(): Int {
        return surahs.size
    }


    fun setOnClickedListener(f: ((Int) -> Unit)?) {
        listener = f
    }
}