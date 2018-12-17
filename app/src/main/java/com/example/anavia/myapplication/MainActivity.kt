package com.example.anavia.myapplication

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    class CustomAdapter(val miContext: Context,
                        val miRecurso: Int,
                        val miLista: ArrayList<Serie>):ArrayAdapter<Serie>(miContext, miRecurso, miLista)
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = LayoutInflater.from(miContext).inflate(miRecurso,null)
            val valor:TextView = view.findViewById(R.id.)

            return super.getView(position, convertView, parent)
        }
    }
}
