package com.vitantonio.nagauzzi.trafficlight.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitantonio.nagauzzi.trafficlight.data.repository.TrafficLightRepository
import com.vitantonio.nagauzzi.trafficlight.data.repository.TrafficLightRepositoryImpl
import com.vitantonio.nagauzzi.trafficlight.ui.model.TrafficLightUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// 信号機画面のViewModel
// UI層とデータ層を橋渡しし、状態管理とビジネスロジックを担当
class TrafficLightViewModel(
    private val repository: TrafficLightRepository = TrafficLightRepositoryImpl()
) : ViewModel() {

    // UI状態を保持するMutableStateFlow（内部用）
    private val _uiState = MutableStateFlow(TrafficLightUiState())

    // UIに公開される読み取り専用のStateFlow
    val uiState: StateFlow<TrafficLightUiState> = _uiState.asStateFlow()

    init {
        // ViewModelの初期化時に信号機の自動更新を開始
        startTrafficLightCycle()
    }

    // 信号機の自動更新サイクルを開始
    private fun startTrafficLightCycle() {
        viewModelScope.launch {
            repository.observeTrafficLightState()
                .collect { trafficLightState ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            trafficLightState = trafficLightState
                        )
                    }
                }
        }
    }
}
