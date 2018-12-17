package com.example.anavia.mycalculator

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            val n1:Long = txtN1.text.toString()?.toLong()
            val n2:Long = txtN2.text.toString()?.toLong()
            val n3:Long = n1 + n2
            var t = Toast.makeText(this, " Suma de $n1 + $n2 = $n3", Toast.LENGTH_LONG).apply {
                setGravity( Gravity.CENTER, 0,0)
                show()
            }
            txtN1.setBackgroundColor( Color.LTGRAY)
        }

        btnMultiply.setOnClickListener {
            val n1:Long = txtN1.text.toString()?.toLong()
            val n2:Long = txtN2.text.toString()?.toLong()
            val n3:Long = n1 * n2
            var t = Toast.makeText(this, " Multiply of $n1 * $n2 = $n3", Toast.LENGTH_LONG).apply {
                setGravity( Gravity.CENTER, 0,0)
                show()
            }
            txtN1.setBackgroundColor( Color.YELLOW)
        }


        btnSubstration.setOnClickListener {
            val n1:Long = txtN1.text.toString()?.toLong()
            val n2:Long = txtN2.text.toString()?.toLong()
            val n3:Long = n1 - n2
            var t = Toast.makeText(this, " Substract of $n1 - $n2 = $n3", Toast.LENGTH_LONG).apply {
                setGravity( Gravity.CENTER, 0,0)
                show()
            }
            txtN1.setBackgroundColor( Color.BLUE)
        }

        btnDivide.setOnClickListener {
            val n1:Double = txtN1.text.toString()?.toDouble()
            val n2:Double = txtN2.text.toString()?.toDouble()
            var n3:Double
            var resultado = ""
            try {
                n3 = n1/n2
                resultado = "Divide $n1 / $n2 = $n3"
            } catch(e: ArithmeticException) {

                resultado = e.message.toString()
            }

            var t = Toast.makeText(this, resultado, Toast.LENGTH_LONG).apply {
                setGravity( Gravity.CENTER, 0,0)
                show()
            }
            txtN1.setBackgroundColor( Color.MAGENTA)
        }

    }
}
