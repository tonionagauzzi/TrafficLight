package com.vitantonio.nagauzzi.trafficlight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.vitantonio.nagauzzi.trafficlight.ui.screens.TrafficLightScreen
import com.vitantonio.nagauzzi.trafficlight.ui.theme.TrafficLightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrafficLightTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrafficLightScreen()
                }
            }
        }
    }
}
