package com.example.splash_table_kotli

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.math.absoluteValue


class AvionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avion)


        //recoger las edtit text en variables

        val nombre = findViewById<EditText>(R.id.nombre_tickete)
        val apellido = findViewById<EditText>(R.id.apellido_tickete)
        var cantidadPersonas = findViewById<RadioGroup>(R.id.radioGroup2).checkedRadioButtonId
        var numeroCedula = findViewById<EditText>(R.id.numeroDeCedula)
        val numeroCedulaInt = numeroCedula.text.toString().toIntOrNull()
        var cantidadSDtring = getString(R.string.dos_personas)


        //crear el listener del boton para mandar la informacion de vuelta
        val buttonGuardar = findViewById<Button>(R.id.boton_guardar_avion)

        buttonGuardar.setOnClickListener{
            if (cantidadPersonas == R.id.unaPersona){
            cantidadSDtring = getString(R.string.una_persona)
        }
            if (nombre.text.toString() == "" || apellido.text.toString() == "" || numeroCedulaInt == null
                || numeroCedulaInt.toString().length >= 8){
                Toast.makeText(this,"Favor rellenar los campos solicitados",
                    Toast.LENGTH_SHORT).show()
                nombre.requestFocus()
            }else{

                var tickete =  Ticketes(nombre.text.toString(),apellido.text.toString(),numeroCedulaInt,cantidadSDtring)


                var intent = Intent()
                intent.putExtra("Tickete",tickete)

                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }



    }
}
