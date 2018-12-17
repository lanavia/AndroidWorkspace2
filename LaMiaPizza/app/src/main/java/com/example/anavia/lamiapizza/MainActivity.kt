package com.example.anavia.lamiapizza

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOk.setOnClickListener {


            /*  val n1:Long = txtN1.text.toString()?.toLong()
               val n2:Long = txtN2.text.toString()?.toLong()
                val n3:Long = n1 * n2
                var t = Toast.makeText(this, " Multiply of $n1 * $n2 = $n3", Toast.LENGTH_LONG).apply {
                    setGravity( Gravity.CENTER, 0,0)
                    show()
                }
                txtN1.setBackgroundColor( Color.YELLOW)*/
        }
    }


}
