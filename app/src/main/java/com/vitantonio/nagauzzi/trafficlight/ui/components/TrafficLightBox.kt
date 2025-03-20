package com.vitantonio.nagauzzi.trafficlight.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vitantonio.nagauzzi.trafficlight.domain.LightColor

@Composable
fun TrafficLightBox(
    activeLight: LightColor,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(3f)
            .background(
                color = Color.Gray,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TrafficLight(
                color = Color.Green,
                isActive = activeLight is LightColor.Green,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            TrafficLight(
                color = Color.Yellow,
                isActive = activeLight is LightColor.Yellow,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            TrafficLight(
                color = Color.Red,
                isActive = activeLight is LightColor.Red,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun TrafficLightBoxPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TrafficLightBox(activeLight = LightColor.Green)
        TrafficLightBox(activeLight = LightColor.Yellow)
        TrafficLightBox(activeLight = LightColor.Red)
    }
} 