package com.vitantonio.nagauzzi.trafficlight.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun TrafficLight(
    color: Color,
    isActive: Boolean,
    modifier: Modifier = Modifier,
    testTag: String = "",
    colorName: String = ""
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .then(
                if (testTag.isNotEmpty()) Modifier.testTag(testTag) else Modifier
            )
            .semantics {
                if (colorName.isNotEmpty()) {
                    contentDescription = if (isActive) {
                        "${colorName}信号：点灯中"
                    } else {
                        "${colorName}信号：消灯中"
                    }
                }
                // カスタムセマンティクスプロパティでisActive状態を公開
                set(IsActiveKey, isActive)
            }
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                shape = CircleShape
            )
            .background(
                color = if (isActive) color else Color.Gray.copy(alpha = 0.3f)
            )
    )
}

// カスタムセマンティクスキーの定義
val IsActiveKey = SemanticsPropertyKey<Boolean>("IsActive")
