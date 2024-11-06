package com.mexiti.connectarduino_2025_i.controller

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.Handler
import com.mexiti.connectarduino_2025_i.CONNECTION_FAILED
import com.mexiti.connectarduino_2025_i.CONNECTION_SUCCESS
import com.mexiti.connectarduino_2025_i.data.DataExchange
import com.mexiti.connectarduino_2025_i.dataExchangeInstance
import java.util.UUID

private val MY_UUDID: UUID  = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB") //direción de todos los hss05

@SuppressLint("MissingPermission")
class ConnectThread(private val monDevice : BluetoothDevice,
                    private val handler: Handler):Thread() { //se encarga de mandar a segundo la plano la ejecución de los hilos
        private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE){
            monDevice.createRfcommSocketToServiceRecord(MY_UUDID)
        }

    override fun run(){
        mmSocket?.let {
            socket->
            try {
                socket.connect()
                handler.obtainMessage(CONNECTION_SUCCESS).sendToTarget()
            }
            catch (e: Exception){
                handler.obtainMessage(CONNECTION_FAILED).sendToTarget()
            }
            dataExchangeInstance = DataExchange(socket)
        }
    }

}