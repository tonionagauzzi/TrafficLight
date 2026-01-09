package com.vitantonio.nagauzzi.trafficlight.ui.screens

import com.vitantonio.nagauzzi.trafficlight.data.TrafficLightState
import com.vitantonio.nagauzzi.trafficlight.data.repository.FakeTrafficLightRepository
import com.vitantonio.nagauzzi.trafficlight.domain.LightColor
import com.vitantonio.nagauzzi.trafficlight.domain.TrafficLightConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * TrafficLightViewModelのユニットテスト
 *
 * テスト戦略:
 * - フェイクリポジトリを使用してモックライブラリを使わない
 * - StandardTestDispatcherで仮想時計を使用
 * - runTestとadvanceUntilIdle()でコルーチンを制御
 */
@ExperimentalCoroutinesApi
class TrafficLightViewModelTest {

    // 仮想時計を持つテストディスパッチャ
    private val testDispatcher = StandardTestDispatcher()

    // フェイクリポジトリ
    private lateinit var fakeRepository: FakeTrafficLightRepository

    // テスト対象
    private lateinit var viewModel: TrafficLightViewModel

    @Before
    fun setup() {
        // メインディスパッチャをテスト用に置き換え
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeTrafficLightRepository()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `ViewModel初期化時にrepositoryの初期状態がuiStateに反映される`() = runTest {
        // カスタム設定でリポジトリを作成
        val customConfig = TrafficLightConfig(
            greenDurationSeconds = 10,
            yellowDurationSeconds = 5,
            redDurationSeconds = 15
        )
        fakeRepository = FakeTrafficLightRepository(customConfig)
        viewModel = TrafficLightViewModel(fakeRepository)

        // ViewModelの初期化処理を完了させる
        advanceUntilIdle()

        // 初期状態でisRunningがtrueであることを確認
        assertEquals(true, viewModel.uiState.value.isRunning)

        // repositoryの初期状態がuiStateに反映されていることを確認
        val uiState = viewModel.uiState.value
        assertNotNull(uiState)
        assertNotNull(uiState.trafficLightState)
        val expectedState = fakeRepository.getCurrentState()
        assertEquals(expectedState, uiState.trafficLightState)
    }

    @Test
    fun `複数の状態変更が順次正しく反映される`() = runTest {
        viewModel = TrafficLightViewModel(fakeRepository)

        // ViewModelの初期化処理を完了させる
        advanceUntilIdle()

        // Green -> Yellow
        val yellowRemainingSeconds = 3
        val yellowState = TrafficLightState(
            activeLight = LightColor.Yellow,
            remainingSeconds = yellowRemainingSeconds
        )
        fakeRepository.emitState(yellowState)
        advanceUntilIdle()
        assertEquals(yellowState, viewModel.uiState.value.trafficLightState)
        assertEquals(
            yellowRemainingSeconds,
            viewModel.uiState.value.trafficLightState.remainingSeconds
        )
        assertEquals(LightColor.Yellow, viewModel.uiState.value.trafficLightState.activeLight)

        // Yellow -> Red
        val redRemainingSeconds = 20
        val redState = TrafficLightState(
            activeLight = LightColor.Red,
            remainingSeconds = redRemainingSeconds
        )
        fakeRepository.emitState(redState)
        advanceUntilIdle()
        assertEquals(redState, viewModel.uiState.value.trafficLightState)
        assertEquals(redRemainingSeconds, viewModel.uiState.value.trafficLightState.remainingSeconds)
        assertEquals(LightColor.Red, viewModel.uiState.value.trafficLightState.activeLight)

        // Red -> Green
        val greenRemainingSeconds = 37
        val greenState = TrafficLightState(
            activeLight = LightColor.Green,
            remainingSeconds = greenRemainingSeconds
        )
        fakeRepository.emitState(greenState)
        advanceUntilIdle()
        assertEquals(greenState, viewModel.uiState.value.trafficLightState)
        assertEquals(
            greenRemainingSeconds,
            viewModel.uiState.value.trafficLightState.remainingSeconds
        )
        assertEquals(LightColor.Green, viewModel.uiState.value.trafficLightState.activeLight)
    }
}
