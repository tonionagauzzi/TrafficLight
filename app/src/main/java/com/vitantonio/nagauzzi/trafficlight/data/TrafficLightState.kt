package com.vitantonio.nagauzzi.trafficlight.data

import com.vitantonio.nagauzzi.trafficlight.domain.LightColor

// 信号機の状態を表すデータクラス
// Pure data: 状態のみを保持し、振る舞いは持たない
data class TrafficLightState(
    val activeLight: LightColor,
    val remainingSeconds: Int
)
