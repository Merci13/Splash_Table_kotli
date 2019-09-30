package com.example.splash_table_kotli

import android.app.Activity
import android.content.Context

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager


import android.widget.*



class AvionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avion)


        //recoger las edtit text en variables

        val nombre = findViewById<EditText>(R.id.nombre_tickete)
        val apellido = findViewById<EditText>(R.id.apellido_tickete)
        var cantidadPersonas = findViewById<RadioGroup>(R.id.radioGroup2).checkedRadioButtonId
        var numeroCedula = findViewById<EditText>(R.id.numeroDeCedula)

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


}
