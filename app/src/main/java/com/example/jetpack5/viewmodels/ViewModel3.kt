package com.example.jetpack5.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpack5.models.CalcularState
import com.example.jetpack5.views.calcularDescuento
import com.example.jetpack5.views.calcularPrecio

class ViewModel3 : ViewModel () {

    var estado by mutableStateOf(CalcularState())
        private set

    fun onValue(value:String,text:String){
        when(text){
            "precio" ->{ estado = estado.copy(precio = value)}
            "descuento" ->{estado = estado.copy(descuento = value)}
        }
    }

    /* v1
    fun calcular(){
        val precio = estado.precio
        val descuento = estado.descuento
        if (precio != "" && descuento != "") {
            estado = estado.copy(
                precioDescuento = calcularPrecio(precio.toDouble(),descuento.toDouble()),
                totalDescuento = calcularDescuento(precio.toDouble(),descuento.toDouble())
            )
        } else {
            estado = estado.copy(
                showAlert = true
            )
        }
    }
     */

    //v2
    fun calcular(){
        val precio = estado.precio
        val descuento = estado.descuento
        estado = if (precio != "" && descuento != "") {
            estado.copy(
                precioDescuento = calcularPrecio(precio.toDouble(),descuento.toDouble()),
                totalDescuento = calcularDescuento(precio.toDouble(),descuento.toDouble())
            )
        } else {
            estado.copy(
                showAlert = true
            )
        }
    }

    private fun calcularPrecio(precio:Double,descuento:Double) :Double {
        val res = precio - calcularDescuento(precio,descuento)
        return kotlin.math.round(res * 100)/100.0

    }

    private fun calcularDescuento(precio:Double,descuento: Double):Double{
        val res = precio * (1 - descuento/100)
        return kotlin.math.round(res * 100)/100.0
    }

    fun limpiar(){
        estado = estado.copy(
            precio = "",
            descuento = "",
            precioDescuento = 0.0,
            totalDescuento = 0.0
        )
    }

    fun confirmAlert(){
        estado = estado.copy(
            showAlert = false
        )
    }
}