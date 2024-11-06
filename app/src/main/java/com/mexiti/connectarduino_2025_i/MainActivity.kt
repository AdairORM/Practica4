package com.mexiti.connectarduino_2025_i

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mexiti.connectarduino_2025_i.controller.connectHC05
import com.mexiti.connectarduino_2025_i.data.DataExchange
import com.mexiti.connectarduino_2025_i.ui.theme.ConnectArduino2025ITheme
import com.mexiti.connectarduino_2025_i.ui.views.BluetoothUI

const val CONNECTION_FAILED: Int = 0
const val CONNECTION_SUCCESS: Int = 1
var dataExchangeInstance: DataExchange? = null

class MainActivity : ComponentActivity() {
    private lateinit var bluetoothManager: BluetoothManager
    private lateinit var bluetoothAdapter: BluetoothAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothManager = getSystemService(BluetoothManager:: class.java)
        bluetoothAdapter = bluetoothManager.adapter //controlar los dispositivos disponilbes bluetooth

        val status = mutableStateOf("Bluetooth & Arduino \n")
        //var connection =  mutableStateOf("Connection Correct")
        val handler = Handler(Looper.getMainLooper()){
            msg->
            when(msg.what){
                CONNECTION_FAILED->{
                    status.value+= "Conexión Rechazada"
                    true
                }
                CONNECTION_SUCCESS->{
                    status.value+= "Conexión Exitosa"
                    true
                }else ->false
            }
        }
        val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
                isGranted ->
            if( isGranted){
                status.value += "Permisos Aceptados \n Intentando Conexión\n"
                status.value += connectHC05(bluetoothAdapter,handler)
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_LONG).show()
            }
            else{
                status.value += "=====> Permiso Denegado"
                Toast.makeText(applicationContext,"This permission is required " +
                        "to comunicate with Arduino", Toast.LENGTH_LONG).show()
            }
        }
        when{
            ContextCompat.checkSelfPermission(
                applicationContext, android.Manifest.permission.BLUETOOTH) ==
                    PackageManager.PERMISSION_GRANTED -> {
                status.value += connectHC05(bluetoothAdapter, handler)
                Toast.makeText(applicationContext, "Todo en orden, puedes " +
                        "conectar con el bluetooth", Toast.LENGTH_LONG).show()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, android.Manifest.permission.BLUETOOTH
            ) -> {
                Toast.makeText(applicationContext," Es importante dar los permisos" +
                        "para poder conectarse a Arduino", Toast.LENGTH_LONG).show()
            }else -> {
            requestPermission.launch(
                Manifest.permission.BLUETOOTH
            )
        }

        }


    
        enableEdgeToEdge()
        setContent {
                ConnectArduino2025ITheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        BluetoothUI(connectStatus = status,  modifier = Modifier.padding(innerPadding))
                    }
                }
        }
    }
}


