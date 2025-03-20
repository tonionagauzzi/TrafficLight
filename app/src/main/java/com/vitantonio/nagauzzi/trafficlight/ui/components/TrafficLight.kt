package com.vitantonio.nagauzzi.trafficlight.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TrafficLight(
    color: Color,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(4.dp, Color.Black, CircleShape)
            .background(if (isActive) color else color.copy(alpha = 0.3f))
    )
}
