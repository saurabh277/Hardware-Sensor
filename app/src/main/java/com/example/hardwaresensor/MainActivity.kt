package com.example.hardwaresensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import android.hardware.SensorManager as SensorManager1
import androidx.core.content.getSystemService as getSystemService1

class MainActivity : AppCompatActivity() {
    lateinit var sensorEventListener: SensorEventListener
    lateinit var  sensorManager: android.hardware.SensorManager
    lateinit var  proxSensor:Sensor
    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as android.hardware.SensorManager
        //A proximity sensor is a sensor able to detect the presence of nearby objects without any physical contact
         proxSensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        sensorEventListener=object :SensorEventListener{
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
              //nothing
                   }

            override fun onSensorChanged(event: SensorEvent?) {
              Log.d("HWSENS","""
                  onSensorChanged: ${event!!.values[0]}
              """.trimIndent())
                }

        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            sensorEventListener,proxSensor,1000*1000

        )

    }

    override fun onPause() {
        sensorManager.unregisterListener(sensorEventListener)
        super.onPause()

    }
}
       /* if(sensorManager==null){
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

    }*/


