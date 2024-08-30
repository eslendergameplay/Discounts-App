package com.example.jetpack5.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack5.components.Alert
import com.example.jetpack5.components.MainButtonOutlined
import com.example.jetpack5.components.MainTextField
import com.example.jetpack5.components.SpaceH
import com.example.jetpack5.components.SpaceV
import com.example.jetpack5.components.TwoCard
import com.example.jetpack5.ui.theme.JetPack5Theme

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(){
    JetPack5Theme(dynamicColor = false) {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text(text = "App Descuentos.", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors
                    (containerColor = MaterialTheme.colorScheme.primary))
            }
        ) {
            ContentHomeView(it)
        }
    }
}

@Composable
fun ContentHomeView(paddingvalues:PaddingValues) {
    var precio by remember { mutableStateOf("") }
    var descuento by remember { mutableStateOf("") }
    var precioDescuento by remember { mutableDoubleStateOf(0.0) }
    var totalDescuento by remember { mutableDoubleStateOf(0.0) }
    var showAlert by remember { mutableStateOf((false)) }
    Column(
        modifier = Modifier
            .padding(paddingvalues)
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TwoCard(
            title1 = "Total.",
            number1 = totalDescuento,
            title2 = "Descuento",
            number2 = precioDescuento
        )
        SpaceV(20.dp)
        MainTextField(value = precio, onValueChange = { precio = it }, label2 = "Precio :")
        SpaceV()
        MainTextField(value = descuento, onValueChange = { descuento = it }, label2 = "Descuento :")
        SpaceV(10.dp)
        MainButtonOutlined(texto = "Generar Descuento.") {
            if (precio != "" && descuento != "") {
                precioDescuento = calcularPrecio(precio.toDouble(),descuento.toDouble())
                totalDescuento = calcularDescuento(precio.toDouble(),descuento.toDouble())
            } else {
                showAlert = true
            }
        }
        SpaceV()
        MainButtonOutlined(texto = "Limpiar.", color = Color.Red) {
            precio = ""
            descuento = ""
            precioDescuento = 0.0
            totalDescuento = 0.0
        }

        if (showAlert) {
            Alert(title = "Alerta.", message = "Escribe el precio y descuento.",
                confirmText = "Aceptar.", onConfirmClick = { showAlert = false }) {//Dismiss}
            }
        }
    }

}
fun calcularPrecio(precio:Double,descuento:Double) :Double {
    val res = precio - calcularDescuento(precio,descuento)
    return kotlin.math.round(res * 100)/100.0

}

fun calcularDescuento(precio:Double,descuento: Double):Double{
    val res = precio * (1 - descuento/100)
    return kotlin.math.round(res * 100)/100.0
}