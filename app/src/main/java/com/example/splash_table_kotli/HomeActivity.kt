package com.example.splash_table_kotli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {

    private val tiqueteAvion = arrayOf<String>()
    private val tiquete_de_avion: ArrayList<Ticketes> = arrayListOf<Ticketes>()







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val avionButton = findViewById<Button>(R.id.button)
        val pizzaButton = findViewById<Button>(R.id.button3)
        avionButton.setOnClickListener{
          startActivity(Intent(this@HomeActivity,AvionActivity::class.java))
            }
        pizzaButton.setOnClickListener{
            startActivity(Intent(this@HomeActivity,PizzaTable::class.java))
        }




    }



}


