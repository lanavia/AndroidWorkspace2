package com.example.anavia.clase11

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity(),GetData.OnTaskCompleted {
    override fun OnTaskCompleted(response: String) {
        if (!response.equals("error")) {
            try {
                /*var lista = ArrayList<String>()
                var json = JSONObject(response)
                var jsonData = json.optJSONArray("serie")
                    Toast.makeText(this," largo arreglo ${jsonData.length()}",Toast.LENGTH_SHORT).show()

                for (i in 0..jsonData.length()-1){
                    var dolar = jsonData.getJSONObject(i)
                    var fecha = dolar.getString("fecha")

                    var texto = dolar.getString("valor")
                    lista.add("$fecha - $texto - $i" )
                }

                var adaptador = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista)
                lvListar.adapter = adaptador
            */

                var json = JSONObject(response)
                var jsonDataUSD = json.getJSONObject("dolar").getString("valor").toInt()




                var jsonDataEUR = json.getJSONObject("euro").getString("valor").toInt()

                val textView = findViewById<EditText>(R.id.txtAmount)
                val monto = textView.text.toString()


               // Toast.makeText(this," dolar $jsonDataUSD euro $jsonDataEUR convertir $monto",Toast.LENGTH_SHORT).show()

            } catch (e:Exception) {
                Log.e("JSon Error", "${e.message},,${e.cause}")

            }
        } else {
            var alerta = AlertDialog.Builder(this)
            alerta.setTitle("Error fatal")
            alerta.setMessage("ha ocurrido un error")
            alerta.setNegativeButton("ok", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            alerta.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //cargar la información del spinner
        val typeOfMoney = arrayOf ("CLP", "EUR", "USD")

        // Adaptador para spinner
        spnMoneyFrom.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, typeOfMoney )
        spnMoneyTo.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, typeOfMoney )

        btnExchange.setOnClickListener {


            var tarea = GetData("", this)
            tarea.execute("https://mindicador.cl/api/")
        }
    }
}

class TypeOFMoney{


}