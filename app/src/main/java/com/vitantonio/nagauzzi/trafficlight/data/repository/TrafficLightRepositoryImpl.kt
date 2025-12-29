package com.vitantonio.nagauzzi.trafficlight.data.repository

import com.vitantonio.nagauzzi.trafficlight.data.TrafficLightState
import com.vitantonio.nagauzzi.trafficlight.domain.LightColor
import com.vitantonio.nagauzzi.trafficlight.domain.TrafficLightConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// TrafficLightRepositoryの実装クラス
// 信号機のビジネスロジックを実装
class TrafficLightRepositoryImpl(
    private val config: TrafficLightConfig = TrafficLightConfig()
) : TrafficLightRepository {

    override fun getNextColor(currentColor: LightColor): LightColor {
        return config.getNextColor(currentColor)
    }

    override fun getDuration(light: LightColor): Int {
        return config.getDuration(light)
    }

    override fun getConfig(): TrafficLightConfig {
        return config
    }

    override fun observeTrafficLightState(): Flow<TrafficLightState> = flow {
        var currentState = TrafficLightState(
            activeLight = LightColor.Green,
            remainingSeconds = config.getDuration(LightColor.Green)
        )

        while (true) {
            val duration = getDuration(currentState.activeLight)

            // 残り時間をカウントダウン
            for (remaining in duration downTo 1) {
                emit(currentState.copy(remainingSeconds = remaining))
                delay(1000L)
            }

            // 次の信号色に遷移
            val nextColor = getNextColor(currentState.activeLight)
            currentState = TrafficLightState(
                activeLight = nextColor,
                remainingSeconds = getDuration(nextColor)
            )
        }
    }
}
