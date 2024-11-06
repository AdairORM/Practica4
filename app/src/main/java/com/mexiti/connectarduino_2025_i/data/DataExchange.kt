package com.mexiti.connectarduino_2025_i.data

import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class DataExchange (mmSocket: BluetoothSocket):Thread(){
    private val length = 2

    private  val mmInStream: InputStream = mmSocket.inputStream //el puerto va a recibir un tipo de entrada
    private val mmOutStream: OutputStream = mmSocket.outputStream
    private val mmBuffer: ByteArray = ByteArray(length) //buffer que almacena, memoria temporal

    fun write(bytes: ByteArray){
        try{
            mmOutStream.write(bytes)
        }
        catch (error: Exception){
            Log.e("Byte Error", "Message didn´t send")
        }
    }

    fun read():String{
        var numBytesReaded = 0
        try{
            while (numBytesReaded < length){
                val number = mmInStream.read(mmBuffer, numBytesReaded, length - numBytesReaded)

                if(number == -1)
                {
                    break
                }
                numBytesReaded += number
            }
            Log.d("TAGByte","Number bytes" + "${numBytesReaded} \n" + "Dato: ${mmBuffer}") //rescatamos el primer elemento del buffer
            return mmBuffer[0].toString()
        }
        catch (e:IOException){
            return "eRROr"
        }
    }



}