@file:OptIn(ExperimentalMaterial3Api::class)

package com.mexiti.connectarduino_2025_i.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mexiti.connectarduino_2025_i.R
import com.mexiti.connectarduino_2025_i.dataExchangeInstance
import com.mexiti.connectarduino_2025_i.ui.theme.ConnectArduino2025ITheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults

@Composable
fun BluetoothUI(connectStatus: MutableState<String>, modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(false) }
    val sensor = remember { mutableStateOf("sin mensaje") }
    var switchControl by remember { mutableStateOf("LED OFF") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECEFF1)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Barra superior
        TopAppBar(
            title = {
                Text(
                    text = "Arduino Bluetooth",
                    fontSize = 20.sp,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF00796B)
            ),
            actions = {
                Icon(
                    imageVector = Icons.Filled.Bluetooth,
                    contentDescription = "Bluetooth",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Imágenes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.pngimg_com___bluetooth_png72),
                contentDescription = "Bluetooth",
                modifier = Modifier.size(80.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.clipart1179524),
                contentDescription = "Arduino",
                modifier = Modifier.size(80.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Estado de conexión
        Text(
            text = connectStatus.value,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(Color(0xFFD7CCC8))
                .padding(start = 16.dp),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Control de LED con interruptor
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = switchControl, fontSize = 16.sp)
            Switch(
                checked = checked,
                onCheckedChange = {
                    if (!checked) {
                        switchControl = "LED ON"
                        dataExchangeInstance?.write("A".toByteArray())
                        checked = true
                    } else {
                        switchControl = "LED OFF"
                        dataExchangeInstance?.write("B".toByteArray())
                        checked = false
                    }
                },
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para leer y mostrar el valor del sensor
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = {
                    val str = dataExchangeInstance?.read()
                    if (str != null) {
                        sensor.value = "${str} [°C]"
                    } else {
                        connectStatus.value = "Sin mensaje"
                    }
                },
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(text = "READ", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = sensor.value,
                modifier = Modifier
                    .background(Color(0xFFD7CCC8))
                    .padding(horizontal = 16.dp),
                fontSize = 16.sp
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun BluetoothUIPreview() {
    ConnectArduino2025ITheme {
        val status = mutableStateOf("Conectado")
        BluetoothUI(connectStatus = status)
    }
}
