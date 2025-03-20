package com.vitantonio.nagauzzi.trafficlight.domain

// 信号機の色を表すsealed interface
sealed interface LightColor {
    val durationSeconds: Int
    val nextColor: LightColor

    data object Green : LightColor {
        override val durationSeconds = 37  // 青信号の表示時間: 37秒
        override val nextColor: LightColor = Yellow
    }

    data object Yellow : LightColor {
        override val durationSeconds = 3   // 黄信号の表示時間: 3秒
        override val nextColor: LightColor = Red
    }

    data object Red : LightColor {
        override val durationSeconds = 20  // 赤信号の表示時間: 20秒
        override val nextColor: LightColor = Green
    }
}
