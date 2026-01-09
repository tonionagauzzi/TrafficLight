package com.vitantonio.nagauzzi.trafficlight.ui.screens

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.SemanticsMatcher
import com.vitantonio.nagauzzi.trafficlight.data.TrafficLightState
import com.vitantonio.nagauzzi.trafficlight.domain.LightColor
import com.vitantonio.nagauzzi.trafficlight.ui.components.IsActiveKey
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * TrafficLightContentのローカルUIテスト
 *
 * Robolectricを使用してJVM上でAndroidフレームワークをシミュレートし、
 * Compose UIの動作を検証します。
 */
@RunWith(RobolectricTestRunner::class)
class TrafficLightContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 緑信号が表示されることを検証() {
        // Given: 緑信号の状態
        val state = TrafficLightState(
            activeLight = LightColor.Green,
            remainingSeconds = 30
        )

        // When: TrafficLightContentを表示
        composeTestRule.setContent {
            TrafficLightContent(trafficLightState = state)
        }

        // Then: 緑のライトが点灯している
        composeTestRule
            .onNodeWithTag("light_green")
            .assertIsDisplayed()
            .assert(isActive())

        // Then: 黄と赤のライトは消灯している
        composeTestRule
            .onNodeWithTag("light_yellow")
            .assertIsDisplayed()
            .assert(isNotActive())

        composeTestRule
            .onNodeWithTag("light_red")
            .assertIsDisplayed()
            .assert(isNotActive())

        // Then: 残り時間が表示される
        composeTestRule
            .onNodeWithText("残り時間: 30秒")
            .assertIsDisplayed()
    }

    @Test
    fun 黄色信号が表示されることを検証() {
        // Given: 黄色信号の状態
        val state = TrafficLightState(
            activeLight = LightColor.Yellow,
            remainingSeconds = 5
        )

        // When: TrafficLightContentを表示
        composeTestRule.setContent {
            TrafficLightContent(trafficLightState = state)
        }

        // Then: 黄のライトが点灯している
        composeTestRule
            .onNodeWithTag("light_yellow")
            .assertIsDisplayed()
            .assert(isActive())

        // Then: 緑と赤のライトは消灯している
        composeTestRule
            .onNodeWithTag("light_green")
            .assertIsDisplayed()
            .assert(isNotActive())

        composeTestRule
            .onNodeWithTag("light_red")
            .assertIsDisplayed()
            .assert(isNotActive())

        // Then: 残り時間が表示される
        composeTestRule
            .onNodeWithText("残り時間: 5秒")
            .assertIsDisplayed()
    }

    @Test
    fun 赤信号が表示されることを検証() {
        // Given: 赤信号の状態
        val state = TrafficLightState(
            activeLight = LightColor.Red,
            remainingSeconds = 45
        )

        // When: TrafficLightContentを表示
        composeTestRule.setContent {
            TrafficLightContent(trafficLightState = state)
        }

        // Then: 赤のライトが点灯している
        composeTestRule
            .onNodeWithTag("light_red")
            .assertIsDisplayed()
            .assert(isActive())

        // Then: 緑と黄のライトは消灯している
        composeTestRule
            .onNodeWithTag("light_green")
            .assertIsDisplayed()
            .assert(isNotActive())

        composeTestRule
            .onNodeWithTag("light_yellow")
            .assertIsDisplayed()
            .assert(isNotActive())

        // Then: 残り時間が表示される
        composeTestRule
            .onNodeWithText("残り時間: 45秒")
            .assertIsDisplayed()
    }

    /**
     * 残り時間が0秒の場合も表示されることを検証
     */
    @Test
    fun trafficLightContent_displaysZeroSeconds() {
        // Given: 残り時間が0秒
        val state = TrafficLightState(
            activeLight = LightColor.Red,
            remainingSeconds = 0
        )

        // When: TrafficLightContentを表示
        composeTestRule.setContent {
            TrafficLightContent(trafficLightState = state)
        }

        // Then: 赤のライトが点灯している
        composeTestRule
            .onNodeWithTag("light_red")
            .assertIsDisplayed()
            .assert(isActive())

        // Then: 緑と黄のライトは消灯している
        composeTestRule
            .onNodeWithTag("light_green")
            .assertIsDisplayed()
            .assert(isNotActive())

        composeTestRule
            .onNodeWithTag("light_yellow")
            .assertIsDisplayed()
            .assert(isNotActive())

        // Then: 残り時間が表示される
        composeTestRule
            .onNodeWithText("残り時間: 0秒")
            .assertIsDisplayed()
    }
}

/**
 * ライトが点灯中であることを検証するSemanticsMatcher
 */
private fun isActive() = SemanticsMatcher("isActive = true") {
    it.config.getOrElseNullable(IsActiveKey) { null } == true
}

/**
 * ライトが消灯中であることを検証するSemanticsMatcher
 */
private fun isNotActive() = SemanticsMatcher("isActive = false") {
    it.config.getOrElseNullable(IsActiveKey) { null } == false
}
