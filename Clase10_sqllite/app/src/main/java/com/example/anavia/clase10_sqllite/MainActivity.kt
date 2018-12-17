package com.example.anavia.clase10_sqllite

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDB = CustomSQL(this,"myDB", null,1)

        btnAgregar.setOnClickListener {
            var mensaje = txtNombre.text.toString()
            myDB.insertar(mensaje)

        }

      /*  btnActualizar.setOnClickListener {
            val lista = myDB.listar()
            val adaptador = ArrayAdapter<Registro>(this,android.R.layout.simple_list_item_1,lista)
            lvLista.adapter = adaptador
        }*/

        btnActualizar.setOnClickListener {
            val lista = myDB.listar()
            lvLista.adapter = CustomAdapter(this,R.layout.item_lista,lista)
        }

    }




}

class CustomAdapter(val miContext:Context,
                    val recurso:Int,
                    val lista:ArrayList<Registro>):ArrayAdapter<Registro>(miContext,recurso,lista){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v:View=LayoutInflater.from(miContext).inflate(recurso,null)
        var mensaje = v.findViewById<TextView>(R.id.lblmensaje)
        var boton = v.findViewById<Button>(R.id.btnBorrar)
        var registro =lista[position]
        mensaje.text = registro.mensaje
        boton.setOnClickListener {
            val alerta = AlertDialog.Builder(miContext)
            alerta.setTitle("Eliminar")
            alerta.setMessage("Delete this")
            alerta.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            alerta.setPositiveButton("Si",DialogInterface.OnClickListener { dialog, which ->
                val db = CustomSQL(miContext, "myDB", null, 1)
                db.eliminar(registro.id)
                this.remove(registro)
            })
            alerta.show()
        }

        return v
    }
}
