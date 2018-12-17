package com.example.android.clase12

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            btnUpdate.setOnClickListener {
                var url = "https://mindicador.cl/api/uf"
                val request = Request.Builder().url(url).build()
                val cliente = OkHttpClient()
                cliente.newCall(request).enqueue(object :Callback{
                    override fun onFailure(call: Call?, e: IOException?) {

                    }

                    override fun onResponse(call: Call?, response: Response?) {
                        val respuesta=response?.body()?.string()
                        val gson = GsonBuilder().create()
                        val indicador = gson.fromJson(respuesta, Indicador::class.java)

                        runOnUiThread {
                            val customAdapter = CustomAdapter(this@MainActivity, R.layout.activity_item, indicador.serie)
                            lvListar.adapter = customAdapter
                        }
                    }
                })
            }

    }

    class CustomAdapter(val miContext: Context,
                        val miRecurso: Int,
                        val miLista: ArrayList<Serie>): ArrayAdapter<Serie>(miContext, miRecurso, miLista)
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = LayoutInflater.from(miContext).inflate(miRecurso,null)
            val valor: TextView = view.findViewById(R.id.lblValor)
            val fecha:TextView = view.findViewById(R.id.lblFecha)
            valor.text = miLista[position].valor.toString()
            fecha.text=miLista[position].fecha


            return view
        }
    }
}
