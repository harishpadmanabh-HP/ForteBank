package com.hp.fortebank.PayActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.harishpadmanabh.apppreferences.AppPreferences
import com.hp.fortebank.Benlist
import com.hp.fortebank.R
import com.hp.fortebank.Retro.Retro
import com.hp.fortebank.databinding.ActivityPayBinding
import com.hp.fortebank.models.PayMoneyModel
import com.hp.fortebank.models.UserDetailsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayBinding
    private lateinit var appPreferences: AppPreferences
    private lateinit var viewModel: PayViewModel
    private lateinit var viewmodelFactory: PayViewmodelFactory
    private lateinit var payMoneyModel: PayMoneyModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this, R.layout.activity_pay)
        viewmodelFactory= PayViewmodelFactory(this)
        viewModel = ViewModelProviders.of(this,viewmodelFactory).get(PayViewModel::class.java)

        appPreferences = AppPreferences.getInstance(this, resources.getString(R.string.app_name))

        binding.apply {
            senderName.text = viewModel.setSenderName()
            recieverName.text = viewModel.setRecieverName()
            Log.e("PAY RECIEVER NAME",viewModel.setRecieverName())

            sendButton.setOnClickListener{

                Log.e("PAY FN","called")
                Log.e("sender id",appPreferences.getData("uid"))
                Log.e("ben id for pay ",appPreferences.getData("recievers_id"))
                Log.e("pay money ",money.text.toString())



                Retro().api.PAY_MONEY_MODEL_CALL(appPreferences.getData("uid"),
                        appPreferences.getData("recievers_id"),money.text.toString()).enqueue(object : Callback<PayMoneyModel> {
                    override fun onResponse(call: Call<PayMoneyModel>, response: Response<PayMoneyModel>) {

                        payMoneyModel =response.body()
                        Log.e("PAY STATUS : ",payMoneyModel.status)
                        if(payMoneyModel.status.equals("success")) {
                            Toast.makeText(this@PayActivity, "Payment Successfull", Toast.LENGTH_LONG).show()

                            startActivity(Intent(this@PayActivity,Benlist::class.java))
                        }else
                        {
                            Toast.makeText(this@PayActivity,"Payment not successfull",Toast.LENGTH_LONG).show()

                        }


                    }
                    override fun onFailure(call: Call<PayMoneyModel>, t: Throwable) {

                        Toast.makeText(this@PayActivity,"Pay api failure $t",Toast.LENGTH_LONG).show()

                    }
                })
            }


        }


    }
}
