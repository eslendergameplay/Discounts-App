package com.example.jetpack5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack5.ui.theme.JetPack5Theme
import com.example.jetpack5.viewmodels.ViewModel1
import com.example.jetpack5.viewmodels.ViewModel2
import com.example.jetpack5.viewmodels.ViewModel3
import com.example.jetpack5.views.HomeView
import com.example.jetpack5.views.HomeView1
import com.example.jetpack5.views.HomeView2
import com.example.jetpack5.views.HomeView3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //val VM1:ViewModel1 by viewModels()
        //val VM2:ViewModel2 by viewModels()
        val VM3:ViewModel3 by viewModels()
        setContent {
            //HomeView()
            //HomeView1(VM1)
            //HomeView2(VM2)
            HomeView3(VM3)
        }
    }
}

