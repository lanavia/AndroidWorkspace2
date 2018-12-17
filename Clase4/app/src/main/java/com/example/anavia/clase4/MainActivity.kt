package com.example.anavia.clase4

import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Color

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAceptar.setOnClickListener {
            val mensaje = txtMensaje.text.toString()
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
            txtMensaje.setBackgroundColor( Color.GRAY)
        }

        btnCancelar.setOnClickListener {
            /*val mensaje = txtMensaje.text.clear()
            txtMensaje.setBackgroundColor( Color.WHITE)*/
            finish();
        }
    }

   /* fun accion(view:View){

    }*/
}
