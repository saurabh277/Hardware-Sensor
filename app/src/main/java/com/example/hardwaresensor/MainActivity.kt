package com.example.hardwaresensor

import android.content.Context
import android.hardware.Sensor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import android.hardware.SensorManager as SensorManager1
import androidx.core.content.getSystemService as getSystemService1

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager =  getSystemService(Context.SENSOR_SERVICE) as android.hardware.SensorManager
        if(sensorManager==null){
            Toast.makeText(this,"could not get sensor",Toast.LENGTH_SHORT).show()
           finish()
        }
        else{
           val sensors= sensorManager.getSensorList(Sensor.TYPE_ALL)
           sensors.forEach {
               Log.d("HWSENS","""
                   ${it.name} | ${it.stringType} |${it.vendor}
               """.trimIndent())
           }
        }

    }
}
