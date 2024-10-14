package com.mexiti.connectarduino_2025_i

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.mexiti.connectarduino_2025_i.ui.theme.ConnectArduino2025ITheme
import com.mexiti.connectarduino_2025_i.ui.views.BluetoothUI


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var connection =  mutableStateOf("Connection Correct")


        enableEdgeToEdge()
        setContent {
                ConnectArduino2025ITheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        BluetoothUI(connectStatus = connection,  modifier = Modifier.padding(innerPadding))
                    }
                }
        }
    }
}


