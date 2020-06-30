package com.hp.fortebank.PayActivity

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.harishpadmanabh.apppreferences.AppPreferences
import com.hp.fortebank.R
import com.hp.fortebank.Retro.Retro
import com.hp.fortebank.models.PayMoneyModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayViewModel(var context: Context) : ViewModel() {

    var appPreferences: AppPreferences = AppPreferences(context, context.resources.getString(R.string.app_name))

    lateinit var payMoneyModel:PayMoneyModel
    fun setSenderName(): String {
        return appPreferences.getData("uname")

    }

    fun setRecieverName(): String {
        return appPreferences.getData("recievers_name")

    }





}
