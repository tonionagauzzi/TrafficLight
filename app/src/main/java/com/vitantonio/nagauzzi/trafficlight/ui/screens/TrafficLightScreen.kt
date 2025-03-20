package com.vitantonio.nagauzzi.trafficlight.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vitantonio.nagauzzi.trafficlight.data.TrafficLightState
import com.vitantonio.nagauzzi.trafficlight.domain.LightColor
import com.vitantonio.nagauzzi.trafficlight.ui.components.TrafficLight
import kotlinx.coroutines.delay

@Composable
fun TrafficLightScreen() {
    var state by remember { mutableStateOf(TrafficLightState(LightColor.Green)) }
    var remainingSeconds by remember { mutableIntStateOf(state.activeLight.durationSeconds) }

    // 時間経過による信号の状態変化を管理
    LaunchedEffect(state.activeLight) {
        remainingSeconds = state.activeLight.durationSeconds
        while (remainingSeconds > 0) {
            delay(1000L)
            remainingSeconds--
        }
        state = state.next()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 緑信号
            TrafficLight(
                color = Color.Green,
                isActive = state.activeLight is LightColor.Green
            )
            // 黄信号
            TrafficLight(
                color = Color.Yellow,
                isActive = state.activeLight is LightColor.Yellow
            )
            // 赤信号
            TrafficLight(
                color = Color.Red,
                isActive = state.activeLight is LightColor.Red
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "残り時間: ${remainingSeconds}秒",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrafficLightScreenPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            TrafficLightScreen()
        }
    }
}
