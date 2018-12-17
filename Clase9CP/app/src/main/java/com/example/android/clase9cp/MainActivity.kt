package com.example.android.clase9cp


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.annotation.RequiresApi
import android.support.v4.content.PermissionChecker
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //PRIMERA PARTE DE LA CLASE COMENTADA

     /*   //ahora en las siguientes 3 lineas se esta guardando
        //sin necesidad de apretar el boton btnSave
        var sp = getSharedPreferences("spArchivo", Context.MODE_PRIVATE)
        var mensaje = sp.getString("mensaje", "$")
        lblMsg.text = mensaje


        btnSave.setOnClickListener {
            //se crea shared preference, lo que esta entre comillas es el nombre del archivo
            var sp = getSharedPreferences("spArchivo", Context.MODE_PRIVATE)
            //
            var editor = sp.edit()
            //se le da nombre a la variable que tomara el texto a editar
            var mensaje = txtMensaje.text.toString()
            //se indica si lo que se grabara es string o int
            editor.putString("mensaje",txtMensaje.text.toString())
            //el sgte ejemplo es con int
            editor.putInt("id",9999)
            // se pone commit para que se apliquen los cambios
            editor.commit()
            //se muestra toast que dice que está ok el cambio
            Toast.makeText(this,"Mensaje escrito OK",Toast.LENGTH_SHORT).show()

        }*/



       //SEGUNDA PARTE DE LA CLASE
        /*//programando crear archivo y escribir en el
        btnGuardar.setOnClickListener {
            try {
                //crea un archivo de extension txt
                var archivo = openFileOutput("archivo.txt",Context.MODE_PRIVATE)
                //se graba en el archivo.txt lo que haya en txtMsj y se pasa a ByteArray,
                // ademas de usar UTF_8 para caracteres especiales
                archivo.write(txtMsj.text.toString().toByteArray(Charsets.UTF_8))
                //siempre se debe cerrar el archivo se debe cerrar el
                // archivo para que no quede abierto y no tire error
                archivo.close()
                Toast.makeText(this,"Archivo guardado",Toast.LENGTH_SHORT).show()
            }
            catch (e:Exception) {
                Log.w("error",e.message)
            }
        }

        //configurando listener de boton leer
        btnLeer.setOnClickListener {
            try {
                //en vez de output se hace el opuesto que es input. se indica variable y n de archivo
                var archivo = openFileInput("archivo.txt")

                //para leer el archivo se debe ir caracter por caracter
                //se crea var texto, que tomara el valor del caracter al que se itere
                var texto = ""
                //la variable cadena toma el valor de los bytes (strings) del archivo
                val cadena = archivo.readBytes()
                //se cierra el archivo
                archivo.close()
                //se itera
                for (caracter in cadena) {
                    //texto al usar += toma el valor de caracter
                    //se debe convertir a char para que lea string
                    texto += caracter.toChar()
                }
                //se asigna el texto al label, pra corroborar que se guardo bien
                lblMsg2.text = texto
                Toast.makeText(this,"leido",Toast.LENGTH_SHORT).show()
            }
            catch (e:Exception) {
                //indicar error en caso de que el try no funcione y la app no se cae
                Log.w ("imposible leer, error",e.message)
            }
        }*/

    //TERCERA PARTE DE LA CLASE
        //se programa checkeo que permiso al boton guardar

        btnGuardar.setOnClickListener {
            //aqui se debe usar el Manifest de ANDROID
          //  val permiso = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            //request permission quedara en rojo al principio, pero se debe apretar
            //alt enter y elegir la primera opcion. asi chequea la version antes
            //requestPermissions(permiso,1)

            //la parte de arriba es equivalente a
            if (Build.VERSION.SDK_INT >= 23) {
                val permiso = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            requestPermissions(permiso, 1)
             }
        }





    }

    fun escribirArchivo(){
        Toast.makeText(this,"metodo escribir archivo",Toast.LENGTH_LONG ).show()

        try {

            if (isExternalStorageWritable()&&checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                val ruta= Environment.getExternalStorageDirectory()
                var archivo = File(ruta.absolutePath, "archivo.txt")
                val escritor = FileOutputStream(archivo)
                val mensaje = txtMensaje.text.toString().toByteArray(Charsets.UTF_8)
                escritor.write(mensaje)
                escritor.close()
                Toast.makeText(this,"Archivo guardado",Toast.LENGTH_LONG ).show()
            }else{
                Toast.makeText(this,"Archivo no se pudo guardar", Toast.LENGTH_LONG).show()
            }
        }catch (e:Exception){
            Log.w("memoria externa", "imposible escribir memoria externa")
            Log.e("error, memoria externa", e.message)
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
       // super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    escribirArchivo()
                }else{
                    Toast.makeText(this,"Requiere permisos", Toast.LENGTH_SHORT).show()
                }

            }
            2->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                   leerArchivo()
                }else{
                    Toast.makeText(this,"Requiere permisos", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }



    //aqui se declara el chequeo de permisos y es igual en cualquier app
    fun isExternalStorageWritable():Boolean {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
    }
    fun isExternalStorageReadable():Boolean {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())
    }
    fun checkPermission(permission: String):Boolean{
        val result = PermissionChecker.checkSelfPermission(this,permission)
        return result == PackageManager.PERMISSION_GRANTED
    }


    fun leerArchivo(){
        try {
            if(isExternalStorageReadable() && checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                var sb = StringBuilder()
                val ruta = Environment.getExternalStorageDirectory()
                var archivo = File(ruta.absolutePath, "archivo.txt")
                val fis = FileInputStream(archivo)
                if (fis != null) {
                    val lector = InputStreamReader(fis)
                    val buffer = BufferedReader(lector)
                    var lineas = ""
                    if (Build.VERSION.SDK_INT > 23) {
                        lineas = buffer.lines().collect(Collectors.joining())
                    } else {
                        buffer.forEachLine { s: String -> lineas += s }
                    }
                    sb.append(lineas)
                    fis.close()
                    lblMsg.text = sb.toString()
                }
            }
        }catch (e:java.lang.Exception){
            Log.w("memoria externa", "imposible leer memoria")
            Log.e("error, memoria externa", e.message)
        }
    }
}
