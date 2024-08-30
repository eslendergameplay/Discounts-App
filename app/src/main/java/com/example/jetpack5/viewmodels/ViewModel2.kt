package com.example.jetpack5.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewModel2 : ViewModel () {
    var precio by mutableStateOf("")
        private set

    var descuento by mutableStateOf("")
        private set

    var precioDescuento by mutableDoubleStateOf(0.0)
        private set
    var totalDescuento by mutableDoubleStateOf(0.0)
        private set
    var showAlert by mutableStateOf(false)
        private set

    fun onValuePrecio(value:String){
        precio = value
    }

    fun onValueDescuento(value:String){
        descuento = value
    }

    fun onValue(value:String,text:String){
        when(text){
            "precio" ->{precio = value}
            "descuento" ->{descuento = value }
        }
    }

    fun calcular(){
        if(precio != "" && descuento != ""){
            precioDescuento = calcularPrecio(precio.toDouble(),descuento.toDouble())
            totalDescuento = calcularDescuento(precio.toDouble(),descuento.toDouble())
        }else{
            showAlert = true
        }

    }

    fun limpiar(){
        precio = ""
        descuento = ""
        precioDescuento = 0.0
        totalDescuento = 0.0
    }

    fun confirmAlert(){
        showAlert = false
    }

    private fun calcularPrecio(precio:Double,descuento:Double) :Double {
        val res = precio - calcularDescuento(precio,descuento)
        return kotlin.math.round(res * 100)/100.0

    }

    private fun calcularDescuento(precio:Double,descuento: Double):Double{
        val res = precio * (1 - descuento/100)
        return kotlin.math.round(res * 100)/100.0
    }
}