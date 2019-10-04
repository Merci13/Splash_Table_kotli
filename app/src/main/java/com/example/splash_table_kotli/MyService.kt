package com.example.splash_table_kotli

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.IpSecManager
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.os.postDelayed
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlin.concurrent.thread

class MyService : Service() {


    var timer = 0
    //Crear el metodo del timer
    var handler = Handler()
    var receiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            p1?.getIntExtra("Tiempo",15).let {
                if (it == null)
                    timer = 15
                else timer = it
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
        showlog("lol")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        showlog("onStart")
        intent?.getIntExtra("Tiempo",15).let {
            if (it == null)
                timer = 15
            else timer = it
        }
        handler.postDelayed(runnable,timer.toLong())
        LocalBroadcastManager.getInstance(this@MyService).registerReceiver(receiver, IntentFilter("RESET"))

        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        showlog("onDestroy")
        LocalBroadcastManager.getInstance(this@MyService).unregisterReceiver(receiver)
        handler.removeCallbacksAndMessages(null)
        Log.d("METODO DESTROY------->","TEST")
        super.onDestroy()



    }


    override fun onBind(intent: Intent): IBinder? {
      return null
    }

    fun showlog(message : String){
        Log.d("Message","MyService" )

    }


    /***
     * Runable para correr el temporizador
     */
    var runnable = object :Runnable {
            override fun run() {
                    timer--
                        if (timer == 0){
                            handler.removeCallbacks(null)

                            LocalBroadcastManager.getInstance(this@MyService).sendBroadcast(
                                Intent("STOP")
                            )


                        }else{
                            Log.d("Timer------->","$timer")
                            handler.postDelayed(this,1000)//espera un segundo


                        }


    }

}



}


