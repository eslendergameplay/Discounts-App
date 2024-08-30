package com.example.jetpack5.views

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
import com.example.jetpack5.components.SpaceV
import com.example.jetpack5.components.TwoCard
import com.example.jetpack5.ui.theme.JetPack5Theme
import com.example.jetpack5.viewmodels.ViewModel3


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView3(viewModel3: ViewModel3){
    JetPack5Theme(dynamicColor = false) {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text(text = "App Descuentos.", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors
                    (containerColor = MaterialTheme.colorScheme.primary))
            }
        ) {
            ContentHomeView3(it,viewModel3)
        }
    }
}

@Composable
fun ContentHomeView3(paddingvalues: PaddingValues,viewModel3:ViewModel3) {

    Column(
        modifier = Modifier
            .padding(paddingvalues)
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val estado = viewModel3.estado
        TwoCard(
            title1 = "Total.",
            number1 = estado.totalDescuento,
            title2 = "Descuento",
            number2 = estado.precioDescuento
        )
        SpaceV(20.dp)
        MainTextField(value = estado.precio, onValueChange = { viewModel3.onValue(it,"precio") }, label2 = "Precio :")
        SpaceV()
        MainTextField(value = estado.descuento, onValueChange = { viewModel3.onValue(it,"descuento") }, label2 = "Descuento :")
        SpaceV(10.dp)
        MainButtonOutlined(texto = "Generar Descuento.") {
            viewModel3.calcular()
        }
        SpaceV()
        MainButtonOutlined(texto = "Limpiar.", color = Color.Red) {
            viewModel3.limpiar()
        }

        if (estado.showAlert) {
            Alert(title = "Alerta.", message = "Escribe el precio y descuento.",
                confirmText = "Aceptar.", onConfirmClick = { viewModel3.confirmAlert()  }) {//Dismiss}
            }
        }
    }

}