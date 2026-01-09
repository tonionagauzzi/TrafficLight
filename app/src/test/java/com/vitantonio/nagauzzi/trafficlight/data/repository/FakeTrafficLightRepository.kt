package com.vitantonio.nagauzzi.trafficlight.data.repository

import com.vitantonio.nagauzzi.trafficlight.data.TrafficLightState
import com.vitantonio.nagauzzi.trafficlight.domain.LightColor
import com.vitantonio.nagauzzi.trafficlight.domain.TrafficLightConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * TrafficLightRepositoryのテスト用フェイク実装
 * テストから状態を完全に制御できるようにする
 */
class FakeTrafficLightRepository(
    private val config: TrafficLightConfig = TrafficLightConfig()
) : TrafficLightRepository {

    // テストから制御可能なStateFlow
    private val _stateFlow = MutableStateFlow(
        TrafficLightState(
            activeLight = LightColor.Green,
            remainingSeconds = config.getDuration(LightColor.Green)
        )
    )

    override fun observeTrafficLightState(): Flow<TrafficLightState> = _stateFlow.asStateFlow()

    override fun getNextColor(currentColor: LightColor): LightColor {
        return config.getNextColor(currentColor)
    }

    override fun getDuration(light: LightColor): Int {
        return config.getDuration(light)
    }

    override fun getConfig(): TrafficLightConfig {
        return config
    }

    // テスト用ヘルパーメソッド: 新しい状態をemitする
    fun emitState(state: TrafficLightState) {
        _stateFlow.value = state
    }

    // テスト用ヘルパーメソッド: 現在の状態を取得
    fun getCurrentState(): TrafficLightState = _stateFlow.value
}
