package com.example.splash_table_kotli

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.util.*


class PizzaTable : AppCompatActivity() {
    companion object{
        const val START_CLOCK = "START"
        const val STOP_CLOCK = "STOP"
        lateinit var receiver : BroadcastReceiver
        //---------se crean los filters para el broadcasr receiver


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_table)
//-----------------se recogen los datos de la ventana para mandarlas a la ordern
        var name = findViewById<EditText>(R.id.nombre_pizza)
        var apellido = findViewById<EditText>(R.id.apellido_pizza)

        //Crear los textWatchers
        val textWatcher = object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //metodo para enviar al broadcast receiver del servicio que se resetee
                LocalBroadcastManager.getInstance(this@PizzaTable).sendBroadcast(
                    Intent("RESET")
                )
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        }
        //agrega los textwatcher a los edittext
        name.addTextChangedListener(textWatcher)
        apellido.addTextChangedListener(textWatcher)








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
                val intent2 = Intent(this@PizzaTable, MyService::class.java)
                val x = stopService(intent2)
                finish()
            }

        }





        //Cerrar el Avtivity
        receiver = object : BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                val intent = Intent(this@PizzaTable, MyService::class.java)
               val x = stopService(intent)
              finish()


            }

        }


        //llamar al servicio
        val intent = Intent(this, MyService::class.java)
        startService(intent)







    }//fin del onCreate


    //Destruye el broadcast
    override fun onStop() {
        LocalBroadcastManager.getInstance(this@PizzaTable).unregisterReceiver(receiver)
        super.onStop()

    }
    override fun onStart() {

        LocalBroadcastManager.getInstance(this@PizzaTable).registerReceiver(receiver, IntentFilter("STOP"))

        super.onStart()
    }
}
