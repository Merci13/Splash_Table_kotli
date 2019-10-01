package com.example.splash_table_kotli

import android.app.Activity
import android.content.Context

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager


import android.widget.*



class AvionActivity : AppCompatActivity() {

    var myTimer = MyTimer()

    companion object {
        private const val TIMER:Int = 15 //constante para el timer
        var mainHandler:Handler = Handler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avion)
        myTimer.conteo = 0
        myTimer.start()


        //recoger las edtit text en variables

        val nombre = findViewById<EditText>(R.id.nombre_tickete)
        val apellido = findViewById<EditText>(R.id.apellido_tickete)
        var cantidadPersonas = findViewById<RadioGroup>(R.id.radioGroup2).checkedRadioButtonId
        var numeroCedula = findViewById<EditText>(R.id.numeroDeCedula)

        nombre.addTextChangedListener(textWatcher)
        apellido.addTextChangedListener(textWatcher)
        numeroCedula.addTextChangedListener(textWatcher)

        //da problemas a la hora de recoger el dato, setea por default el NULL en vez de el valor que
        //fue puesto por el usuario






        var cantidadSDtring = getString(R.string.dos_personas)


        //crear el listener del boton para mandar la informacion de vuelta
        val buttonGuardar = findViewById<Button>(R.id.boton_guardar_avion)

        buttonGuardar.setOnClickListener{
            var numeroCedulaint = numeroCedula.text.toString().toIntOrNull()

            if (cantidadPersonas == R.id.unaPersona){
            cantidadSDtring = getString(R.string.una_persona)
                Log.d("Numero De Cedula =",numeroCedula.text.toString())
        }
            if (nombre.text.toString() == "" || apellido.text.toString() == "" || numeroCedulaint == null
                || numeroCedulaint.toString().length <= 8){
                Toast.makeText(this,"Favor rellenar los campos solicitados",
                    Toast.LENGTH_SHORT).show()
                //ocultar el teclado
                val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)

                nombre.requestFocus()
            }else{

                var tickete =  Ticketes(nombre.text.toString(),apellido.text.toString(),numeroCedulaint,cantidadSDtring)


                var intent = Intent()
                intent.putExtra("Tickete",tickete)

                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }



    }

    //Text watcher para los EditText
   val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //reiniciar el timer
        }


        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            //cuando no esta escribiendo hacer correr el timer
            myTimer.conteo = 0


        }

        override fun afterTextChanged(p0: Editable?) {


        }
    }


    //funcion del timer
    inner class MyTimer : Thread() {
        var conteo = 0
        override fun run() {


            while (conteo <=TIMER){
                if (conteo < TIMER){
                    Thread.sleep(1000)//espera un segundo
                    Log.d("Timer----------------->", "$conteo")


                }else{
                    mainHandler.post {
                        Log.d("Timer-------Cerrando->", "$conteo")
                        finish()//cierra el activity

                    }


                }

                conteo++

        }


        }

    }

}//fin de la clase