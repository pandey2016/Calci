package com.example.firstapp.calci

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateDisplay("")
    }
    val operationList: MutableList<String> = arrayListOf()
    val numberCache: MutableList<String> = arrayListOf()


  
    fun makeString(list: List<String>, joiner: String = ""): String {

        if (list.isEmpty()) return ""
        return list.reduce { r, s -> r + joiner + s }
    }

   fun updateDisplay(mainDisplayString: String) {

        val fullCalculationString = makeString(operationList, " ")
        var fullCalculationTextView = findViewById(R.id.textView2) as TextView
        fullCalculationTextView.text = fullCalculationString

        val mainTextView = findViewById(R.id.textView1) as TextView
        mainTextView.text = mainDisplayString
    }

   fun buttonClick(view: View) {

        val button = view as Button

        if (numberCache.isEmpty()) return

        operationList.add(makeString(numberCache))
        numberCache.clear()
        operationList.add(button.text.toString())

        
    }

    fun numberClick(view: View) {
        val button = view as Button
        val numberString = button.text;

        numberCache.add(numberString.toString())
        val text = makeString(numberCache);
        updateDisplay(text)
    }
    fun equalsClick(view: View) {
        operationList.add(makeString(numberCache))
        numberCache.clear()

        val calculator = Compute()
        val answer = calculator.calculate(operationList)

        updateDisplay("=" + answer.toString())
        clearCache()
    }
     fun clearCache() {
        numberCache.clear()
        operationList.clear()
    }
}
