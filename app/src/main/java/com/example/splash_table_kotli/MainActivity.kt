package com.example.splash_table_kotli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.core.os.postDelayed

//----constantes----//
private const val SPLASH_TIME_OUT: Long = 5000


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * EL handler post delayed me permite retrasar el llamado del home activity
         *
         *
         * */
        Handler().postDelayed({
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }, SPLASH_TIME_OUT)

    }



}
