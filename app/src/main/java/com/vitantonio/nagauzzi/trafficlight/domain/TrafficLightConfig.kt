package com.vitantonio.nagauzzi.trafficlight.domain

// 信号機の設定を表すデータクラス
// 表示時間などの設定値を保持し、ドメインモデルから分離
data class TrafficLightConfig(
    val greenDurationSeconds: Int = 37,
    val yellowDurationSeconds: Int = 3,
    val redDurationSeconds: Int = 20
) {
    // 指定された色の表示時間を取得
    fun getDuration(light: LightColor): Int = when (light) {
        LightColor.Green -> greenDurationSeconds
        LightColor.Yellow -> yellowDurationSeconds
        LightColor.Red -> redDurationSeconds
    }

    // 次の信号色を取得
    fun getNextColor(currentColor: LightColor): LightColor = when (currentColor) {
        LightColor.Green -> LightColor.Yellow
        LightColor.Yellow -> LightColor.Red
        LightColor.Red -> LightColor.Green
    }
}
