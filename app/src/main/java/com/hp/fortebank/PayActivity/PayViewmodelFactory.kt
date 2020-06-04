package com.hp.fortebank.PayActivity

import android.content.Context
import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.*

class PayViewmodelFactory (private val context: Context) : Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PayViewModel::class.java)) {
            return PayViewModel(context) as T
        }
        throw IllegalMonitorStateException("Unknown viewmodel exception")

    }
}