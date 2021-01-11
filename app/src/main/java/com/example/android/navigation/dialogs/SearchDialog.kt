package com.example.android.navigation.dialogs

import android.content.Context
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.navigation.R
import com.example.android.navigation.adapters.SearchAdapter

class SearchDialog(context: Context) : BaseDialog(context, R.layout.search_view) {
    private var listener: ((Int) -> Unit)? = null
    private  var adapter=SearchAdapter(context)
    var recyclerSearch: RecyclerView = view.findViewById(R.id.recyclerSearch)
    val close_search :Button=view.findViewById(R.id.close_search)

    init {
        view.apply {

            recyclerSearch.adapter = adapter
            recyclerSearch.layoutManager = LinearLayoutManager(context)

            close_search.setOnClickListener {
                close()
            }

            adapter.setOnClickedListener {position->
                listener?.invoke(position)
                close()
            }
        }
    }
    fun setOnSubmitListener(f: ((Int) -> Unit)?) {
        listener = f
    }
}

