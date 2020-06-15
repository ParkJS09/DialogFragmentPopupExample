package com.example.commonpopupexample.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.commonpopupexample.R
import com.example.commonpopupexample.dialog.`interface`.DialogInterface

class CustomDialog : DialogFragment(){

    private lateinit var dialogType : DialogEnum
    private lateinit var callBack : DialogInterface
    fun getInstance() = CustomDialog()
    fun setDialog(dialogType : DialogEnum,callBack : DialogInterface){
        this.dialogType = dialogType
        this.callBack = callBack
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style, theme)
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view = if(dialogType == DialogEnum.ALERT){
            inflater.inflate(R.layout.dialog_alert, container)
        }else{
            inflater.inflate(R.layout.dialog_error, container)
        }

        when(dialogType){
            DialogEnum.ALERT -> {
                view.findViewById<TextView>(R.id.tv_cancle).setOnClickListener { callBack.onCancle() }
                view.findViewById<TextView>(R.id.tv_agree).setOnClickListener { callBack.onAgree() }
            }

            DialogEnum.ERROR -> {
                view.findViewById<TextView>(R.id.tv_agree).setOnClickListener { callBack.onAgree() }
            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        // 레이아웃 크기 및 위치 조정
        if(dialogType == DialogEnum.ALERT){
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog?.window?.setLayout(width, height)
            dialog?.window?.setGravity(Gravity.BOTTOM)
        }else{
            val width = ViewGroup.LayoutParams.WRAP_CONTENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog?.window?.setLayout(width, height)
            dialog?.window?.setGravity(Gravity.RIGHT)
        }

    }


    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
    }

    override fun dismiss() {
        super.dismiss()
    }
}