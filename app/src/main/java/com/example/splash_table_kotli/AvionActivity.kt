package com.example.splash_table_kotli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class AvionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avion)


        //recoger las edtit text en variables

        val nombre = findViewById<EditText>(R.id.nombre_tickete)
        val apellido = findViewById<EditText>(R.id.apellido_tickete)
        var cantidadPersonas = findViewById<RadioGroup>(R.id.radioGroup2).checkedRadioButtonId
        var cantidadSDtring = getString(R.string.dos_personas)

       if (cantidadPersonas == R.id.unaPersona){
           cantidadSDtring = getString(R.string.una_persona)
       }

    }
}
