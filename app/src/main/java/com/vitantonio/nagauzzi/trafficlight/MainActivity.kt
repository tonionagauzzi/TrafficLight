package com.vitantonio.nagauzzi.trafficlight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// 信号機の色を表す列挙型
enum class LightColor {
    GREEN, YELLOW, RED
}

// 信号機の状態を表すデータクラス
data class TrafficLightState(
    val activeLight: LightColor
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
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

@Composable
fun TrafficLightScreen() {
    // 現在点灯している信号の状態を管理
    val state by remember { mutableStateOf(TrafficLightState(LightColor.GREEN)) }

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
                isActive = state.activeLight == LightColor.GREEN
            )
            // 黄信号
            TrafficLight(
                color = Color.Yellow,
                isActive = state.activeLight == LightColor.YELLOW
            )
            // 赤信号
            TrafficLight(
                color = Color.Red,
                isActive = state.activeLight == LightColor.RED
            )
        }
    }
}

@Composable
fun TrafficLight(
    color: Color,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(4.dp, Color.Black, CircleShape)
            .background(if (isActive) color else color.copy(alpha = 0.3f))
    )
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