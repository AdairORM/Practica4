package com.mexiti.connectarduino_2025_i.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mexiti.connectarduino_2025_i.R
import com.mexiti.connectarduino_2025_i.ui.theme.ConnectArduino2025ITheme

@Composable
fun BluetoothUI(connectStatus: MutableState<String>, modifier: Modifier = Modifier){


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row (
             modifier = Modifier.height(128.dp),
            verticalAlignment = Alignment.CenterVertically

        ){
            Image(
                painter = painterResource(id = R.drawable.bluetooth),
                contentDescription ="Bluetooth")
            Image(painter = painterResource(id = R.drawable.arduino),
                contentDescription ="Arduino" )

        }
        Spacer(modifier = Modifier.height(200.dp))

        Text(
            text = connectStatus.value,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(Color(0x80E2EBEA))
                .padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "switchControl")
            Switch(checked = true,
                onCheckedChange ={

                } )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {

            },
                modifier = Modifier.padding(start = 48.dp)
                ) {
                Text(text = " READ ")
            }
            Text(
                text = "25 [°C]",
                modifier = Modifier
                    .padding(start = 96.dp)
                    .background(Color(0x80E2EBEA))
                    .padding(horizontal = 16.dp)
            )
            

        }


    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun BluetoothUIpreview(){
    ConnectArduino2025ITheme {
       val connection = mutableStateOf("Connection correct")

            BluetoothUI(connectStatus = connection)
    }
}