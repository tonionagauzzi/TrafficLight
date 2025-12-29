package com.vitantonio.nagauzzi.trafficlight.domain

// 信号機の色を表すsealed interface
// Pure domain model: データや振る舞いを持たず、概念のみを表現
sealed interface LightColor {
    data object Green : LightColor
    data object Yellow : LightColor
    data object Red : LightColor
}
