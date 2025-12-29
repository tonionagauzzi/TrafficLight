package com.vitantonio.nagauzzi.trafficlight.data.repository

import com.vitantonio.nagauzzi.trafficlight.data.TrafficLightState
import com.vitantonio.nagauzzi.trafficlight.domain.LightColor
import com.vitantonio.nagauzzi.trafficlight.domain.TrafficLightConfig
import kotlinx.coroutines.flow.Flow

// 信号機のデータアクセスを抽象化するRepositoryインターフェース
interface TrafficLightRepository {
    // 次の信号色を取得
    fun getNextColor(currentColor: LightColor): LightColor

    // 指定された信号色の表示時間を取得
    fun getDuration(light: LightColor): Int

    // 信号機の設定を取得
    fun getConfig(): TrafficLightConfig

    // 信号機の状態変化を監視するFlowを取得
    fun observeTrafficLightState(): Flow<TrafficLightState>
}
