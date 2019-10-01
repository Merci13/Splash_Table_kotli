package com.example.splash_table_kotli

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

class PizzaTable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_table)
//-----------------se recogen los datos de la ventana para mandarlas a la ordern
        var name = findViewById<EditText>(R.id.nombre_pizza)
        var apellido = findViewById<EditText>(R.id.apellido_pizza)






        //---se validan que los datos no sean nulos o incorrectos
        var botonGuardar = findViewById<Button>(R.id.btn_OK_Pizza)
        botonGuardar.setOnClickListener{
            
            var tamannoPizza = findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId

            if (name.text.toString() == "" || apellido.text.toString() == ""  ){

                Toast.makeText(this,"Favor rellenar los campos solicitados",
                    Toast.LENGTH_SHORT).show()
                name.requestFocus()

            }else {
                //Validar cual opcion esta siendo seleccionada
                var tamannoGrandeString =getString(R.string.tamannoGrande)
                var tamannoMedianoString =getString(R.string.tamannoMediano)
                var tamannoPequennoString =getString(R.string.tamannoPequenno)

                var nuevotexto=""//texto que se va a agregar a la orden de la pizza

                when(tamannoPizza){
                    R.id.rb_Grande -> nuevotexto = tamannoGrandeString

                    R.id.rb_Mediano -> nuevotexto = tamannoMedianoString

                    else -> nuevotexto = tamannoPequennoString


                }

                Log.d("Tamanno de la pizza", "$nuevotexto")
                //----------------------------Mandar la informacion al activity

                var pedido:OrdenPizza = OrdenPizza(name.text.toString(),apellido.text.toString(),nuevotexto)

                var intent = Intent()
                intent.putExtra("Orden",pedido)

                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        }













    }
}
