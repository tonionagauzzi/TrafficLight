package com.vitantonio.nagauzzi.trafficlight.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vitantonio.nagauzzi.trafficlight.data.TrafficLightState
import com.vitantonio.nagauzzi.trafficlight.domain.LightColor
import com.vitantonio.nagauzzi.trafficlight.ui.components.TrafficLightBox

// 信号機画面のComposable
// ViewModelから状態を受け取り、UIを描画するのみ（ビジネスロジックなし）
@Composable
fun TrafficLightScreen(
    viewModel: TrafficLightViewModel = viewModel()
) {
    // ViewModelからUI状態を収集（Lifecycle-aware）
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TrafficLightContent(
        trafficLightState = uiState.trafficLightState
    )
}

// UI表示部分を分離（テストとプレビューが容易）
@Composable
fun TrafficLightContent(
    trafficLightState: TrafficLightState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TrafficLightBox(
            activeLight = trafficLightState.activeLight
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "残り時間: ${trafficLightState.remainingSeconds}秒",
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
            TrafficLightContent(
                trafficLightState = TrafficLightState(
                    activeLight = LightColor.Green,
                    remainingSeconds = 37
                )
            )
        }
    }
}
