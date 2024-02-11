package com.example.mycalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView?=null
    private var lastNumeric:Boolean=false
    private var lastDot:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.tvInput)
   }


    fun onDigit(View: View){
     tvInput?.append((View as Button).text)
       lastNumeric=true
       lastDot=false }



    fun onClear(View: View){
        tvInput?.text=""
    }



    fun onDecimalPoint(View: View){
        if(lastNumeric && !lastDot)
        {
            tvInput?.append(".")
            lastNumeric=false
            lastDot=true
        }
    }




     fun onOperator(View: View) {
         tvInput?.text?.let {

          if (lastNumeric && !isOperatorAdded(it.toString())) {
            tvInput?.append((View as Button).text)
            lastNumeric = false
            lastDot = true}
         }
     }



    fun onEqual(View: View){
        if(lastNumeric){
           var tvValue=tvInput?.text.toString()
           var prefix=""
                try{
                    if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)}


                    if(tvValue.contains("-")) {
                        val splitValue = tvValue.split("-")

                        var one:String = splitValue[0]
                        val two:String = splitValue[1]

                        if (prefix.isNotEmpty()){one= prefix + one }

              tvInput?.text=removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())

                    }else if(tvValue.contains("+")) {
                           val splitValue = tvValue.split("+")
                           var one:String = splitValue[0]
                           val two:String = splitValue[1]
                        if (prefix.isNotEmpty()){one= prefix + one }

             tvInput?.text=removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                    }else  if(tvValue.contains("*")) {
                        val splitValue = tvValue.split("*")
                        var one:String = splitValue[0]
                        val two:String = splitValue[1]

                        if (prefix.isNotEmpty()){one= prefix + one}

                        tvInput?.text=removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                    }else if(tvValue.contains("/")) {
                            val splitValue = tvValue.split("/")
                            var one:String = splitValue[0]
                            var two:String = splitValue[1]
                            if (prefix.isNotEmpty()){
                                one= prefix + one
                            }
                            tvInput?.text=removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                        }

               }catch (e: java.lang.ArithmeticException){
                    e.printStackTrace()
                }
            }
        }



     @SuppressLint("SuspiciousIndentation")
     private fun removeZeroAfterDot(result: String):String{

         var value = result
         if (result.contains(".0"))
         value = result.substring(0,result.length - 2)
         return value }

    private fun isOperatorAdded(value: String): Boolean{
              return if(value.startsWith("-")) {
                    false
                    }else{
                            value.contains("/")
                                            || value.contains("*")
                                            ||value.contains("+")
                                            || value.contains("-")
                    }
                    }

}