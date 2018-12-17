package com.example.anavia.mysalesmovies

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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


        var url = "https://mindicador.cl/api/uf"
        val request = Request.Builder().url(url).build()
        val detallePelicula = OkHttpClient()
        detallePelicula.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                val respuesta=response?.body()?.string()
                val gson = GsonBuilder().create()
                val pelicula = gson.fromJson(respuesta, Peliculas::class.java)

                runOnUiThread {
                    val customAdapter = CustomAdapter(this@MainActivity, R.layout.abc_activity_chooser_view_list_item, pelicula.detalles)
                    lyPeliculas.adapter = CustomAdapter
                }
            }
        })
    }
}



class CustomAdapter(val miContext: Context,
                    val miRecurso: Int,
                    val miLista: ArrayList<Pelicula>): ArrayAdapter<Pelicula>(miContext, miRecurso, miLista)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(miContext).inflate(miRecurso,null)
        val titulo: TextView = view.findViewById(R.id.txtTitulo)
        val episodio: TextView = view.findViewById(R.id.txtEpisodio)
        val fechaLanzamiento: TextView = view.findViewById(R.id.txtFechaLanzamiento)
        val director: TextView = view.findViewById(R.id.txtDirector)
        val productor: TextView = view.findViewById(R.id.txtProductor)

        titulo.text = miLista[position].titulo.toString()
        episodio.text=miLista[position].episodio.toString()
        fechaLanzamiento.text=miLista[position].fechaLanzamiento.toString()
        director.text=miLista[position].director.toString()
        productor.text=miLista[position].productor.toString()

        return view
    }
}