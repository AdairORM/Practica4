package com.mexiti.connectarduino_2025_i.controller

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.os.Handler

@SuppressLint("MissingPermission")
fun connectHC05(bluetoothAdapter: BluetoothAdapter?, handler: Handler):String{
    val pairDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices //emparejar los dispositivoa
    val hc05Device = pairDevices?.find { it.name == "HC-05" }
    if(hc05Device != null){
        ConnectThread(hc05Device, handler).start()
        return ""
    }
    else{
        return "HC-05 No asociado \n"
    }

}