package com.example.splash_table_kotli

import android.app.Activity
import android.app.VoiceInteractor
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class HomeActivity : AppCompatActivity() {

    companion object ConstantesRequestCode{

        private const val AVION_REQUEST_CODE:Int = 1
        private const val PIZZA_REQUEST_CODE:Int = 2
    }



    private val tiquete_de_avion: ArrayList<Ticketes> = arrayListOf<Ticketes>()
    private val ordenes_pizza: ArrayList<OrdenPizza> = arrayListOf<OrdenPizza>()








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //se localizan los botones y se les asigna a una variable para su uso
        val avionButton = findViewById<Button>(R.id.button)
        val pizzaButton = findViewById<Button>(R.id.button3)


        //se llaman las diferentes ventanas por medio de un activity result
        avionButton.setOnClickListener{
            val intent = Intent(this@HomeActivity, AvionActivity::class.java)

            startActivityForResult(intent,AVION_REQUEST_CODE)
            }
        pizzaButton.setOnClickListener{
           val intent = Intent(this@HomeActivity,PizzaTable::class.java)

            startActivityForResult(intent,PIZZA_REQUEST_CODE )
        }




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /**
         *  1: Avion request code
         *  2: Pizza_Table request code
         * */

        if (requestCode == AVION_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null){


            data?.getParcelableExtra<Ticketes>("Tickete")?.let{tiketes ->
                tiquete_de_avion.add(tiketes)
            }
            //muestra en consola lo que tiene tiquete de avion
            Log.d("TIQUETES---------->", tiquete_de_avion.toString())


        }else if (requestCode == PIZZA_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null){
            data?.getParcelableExtra<OrdenPizza>("orden")?.let{
                ordenes_pizza.add(it)

            }
            Log.d("ORDENES--------->",ordenes_pizza.toString())

        }


    }


}



