package com.example.commonpopupexample

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.example.commonpopupexample.databinding.ActivityMainBinding
import com.example.commonpopupexample.dialog.CustomDialog
import com.example.commonpopupexample.dialog.DialogEnum
import com.example.commonpopupexample.dialog.`interface`.DialogInterface

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val customDialog = CustomDialog().getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btnPopup1.setOnClickListener {
            customDialog.setDialog(DialogEnum.ALERT,object : DialogInterface{
                override fun onAgree() {
                    Log.d("DialogEnum.ALERT", "onAgree")
                    customDialog.dismiss()
                }

                override fun onCancle() {
                    Log.d("DialogEnum.ALERT", "onCancle")
                    customDialog.dismiss()
                }
            })
            customDialog.show(supportFragmentManager, "AlertDialogFragment")
        }
        binding.btnPopup2.setOnClickListener {
            customDialog.setDialog(DialogEnum.ERROR, object : DialogInterface{
                override fun onAgree() {
                    Log.d("DialogEnum.ERROR", "onAgree")
                    customDialog.dismiss()
                }

                override fun onCancle() {
                    Log.d("DialogEnum.ERROR", "onCancle")
                    customDialog.dismiss()
                }
            })
            customDialog.show(supportFragmentManager, "ErrorDialogFragment")
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}
