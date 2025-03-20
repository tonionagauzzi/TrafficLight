package com.vitantonio.nagauzzi.trafficlight.data

import com.vitantonio.nagauzzi.trafficlight.domain.LightColor

// 信号機の状態を表すデータクラス
data class TrafficLightState(
    val activeLight: LightColor
) {
    fun next(): TrafficLightState = TrafficLightState(activeLight.nextColor)
}
