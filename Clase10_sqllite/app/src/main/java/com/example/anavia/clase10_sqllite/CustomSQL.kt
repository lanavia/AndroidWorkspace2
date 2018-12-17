package com.example.anavia.clase10_sqllite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.sql.SQLException

class CustomSQL(val context: Context,
                val name: String,
                val factory: SQLiteDatabase.CursorFactory?,
                var version: Int):SQLiteOpenHelper(context,name,factory,version) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE lista(id INTEGER PRIMARY KEY AUTOINCREMENT, mensaje TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertar(mensaje:String){
        try {
            val db = this.writableDatabase
            //otra forma mas de crear elemento clave valor
            var cv = ContentValues()
            //debe llamarse igual que la columna de la tabla creada
            cv.put("mensaje", mensaje)
            val resultado = db.insert("lista",null,cv)
            db.close()
            if (resultado !=-1L){
                Toast.makeText(context,"Mensaje no agregado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Mensaje agregado", Toast.LENGTH_SHORT).show()
            }

        }catch (e:SQLException){
            Toast.makeText(context,"Error al insertar ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e("sqlListar", e.message)
        }
    }


//listar sin la clase registro
   /* fun listar():ArrayList<String>{
        var lista = ArrayList<String>()
        try {
            val db = this.writableDatabase
            var cursor : Cursor? = null
            cursor = db.rawQuery("select * from lista", null)
            if (cursor.moveToFirst()){
                do {
                    val mensaje = cursor.getString(1)
                    lista.add(mensaje)
                }while (cursor.moveToNext())

            }
            db.close()
            return lista
        }catch (e:SQLException){
            Toast.makeText(context, "Error al listar ${e.message}", Toast.LENGTH_SHORT).show()
        }
        return lista
    }*/


    fun listar(): ArrayList<Registro> {
        var lista = ArrayList<Registro>()
        try {
            val db = this.writableDatabase
            var cursor: Cursor? = null
            cursor = db.rawQuery("select * from lista", null)
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(0)
                    val mensaje = cursor.getString(1)
                    val registro = Registro(id, mensaje)
                    lista.add(registro)
                } while (cursor.moveToNext())

            }
            db.close()
            return lista
        } catch (e: SQLException) {
            Toast.makeText(context, "Error al listar ${e.message}", Toast.LENGTH_SHORT).show()
        }
        return lista

    }

    fun eliminar(id:Int){
        try {
            val db = this.writableDatabase
            val args = arrayOf(id.toString())
            val resultado = db.delete("lista", "id=?",args)
            db.close()
            if (resultado==0){
                Toast.makeText(context, "no se pudo eliminar", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "eliminado", Toast.LENGTH_SHORT).show()
            }

        }catch (e:SQLException){
            Toast.makeText(context,"Error al eliminar ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

}
