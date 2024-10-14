package com.mexiti.connectarduino_2025_i

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mexiti.connectarduino_2025_i.ui.theme.ConnectArduino2025ITheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContent {
            ConnectArduino2025ITheme {

            }
        }
    }
}

