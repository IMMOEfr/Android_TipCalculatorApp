package com.example.tipcalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val costInput: EditText = findViewById(R.id.costInput)      //store user text input as costInput
        val calcBtn: Button = findViewById(R.id.calculateBtn)       //store calculate button state
        val enSwitch: MaterialSwitch = findViewById(R.id.enSwitch)   //store round-off switch
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)   //store radio group
        var tipPercentage: Double = 0.0                             //initialize tip percentage

        //checking for user interaction with radio group
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.amazing -> {
                    tipPercentage = 0.2                         //set tip percentage as 20%
                }

                R.id.good -> {
                    tipPercentage = 0.18                        //set tip percentage as 18%
                }
                R.id.fair -> {
                        tipPercentage = 0.15                   //set tip percentage as 15%
                    }
            }
        }
        //checking for user interaction with calculate button
        calcBtn.setOnClickListener {
            val userInputText: String = costInput.text.toString()    //store user text input as userInputText
            if (userInputText.isEmpty() || radioGroup.checkedRadioButtonId == -1) {  //check if user input or radio button is empty
                findViewById<TextView>(R.id.tipAmount).text = "ERROR"
                findViewById<TextView>(R.id.tipAmount).visibility = View.VISIBLE   //display ERROR message
            }
            else {
                val tipTotal = tipPercentage * userInputText.toDouble()    //calculate tip total
                var formatTipTotal: String = String.format("%.2f", tipTotal) // Default to 2 decimal places
                //checking for user interaction with round-off switch
                if (enSwitch.isChecked) {
                    formatTipTotal = Math.round(tipTotal).toInt().toString() //round-off tip total to nearest whole number
                }
                findViewById<TextView>(R.id.tipAmount).text = formatTipTotal
                findViewById<TextView>(R.id.tipAmount).visibility = View.VISIBLE   //display tip amount
            }
        }
    }
}