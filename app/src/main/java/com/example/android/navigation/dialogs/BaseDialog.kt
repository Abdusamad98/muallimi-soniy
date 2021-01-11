package com.example.android.navigation.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog


abstract class BaseDialog(context: Context, @LayoutRes resId: Int) {
    private val dialog = AlertDialog.Builder(context).create()
    internal val view = LayoutInflater.from(context).inflate(resId, null, false)

    init {
        dialog.setView(view)
    }

    fun show() {
        dialog.show()
    }

    fun close() {
        dialog.dismiss()
    }
}