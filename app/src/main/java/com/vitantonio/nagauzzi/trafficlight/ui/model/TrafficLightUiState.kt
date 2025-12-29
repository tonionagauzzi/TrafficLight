package com.vitantonio.nagauzzi.trafficlight.ui.model

import com.vitantonio.nagauzzi.trafficlight.data.TrafficLightState
import com.vitantonio.nagauzzi.trafficlight.domain.LightColor

// UI層の状態を表すデータクラス
// ViewModelからUIに流れる状態を定義
data class TrafficLightUiState(
    val trafficLightState: TrafficLightState = TrafficLightState(
        activeLight = LightColor.Green,
        remainingSeconds = 37
    ),
    val isRunning: Boolean = true
)
