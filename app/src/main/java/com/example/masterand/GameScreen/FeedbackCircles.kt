package com.example.masterand.GameScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FeedbackCircles(colors: List<Color>) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            SmallCircle(color = colors.getOrNull(0) ?: Color.Transparent)
            SmallCircle(color = colors.getOrNull(1) ?: Color.Transparent)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            SmallCircle(color = colors.getOrNull(2) ?: Color.Transparent)
            SmallCircle(color = colors.getOrNull(3) ?: Color.Transparent)
        }
    }
}

@Preview
@Composable
fun FeedbackCirclesPreview() {
    FeedbackCircles(colors = listOf(Color.Cyan, Color.Magenta, Color.Green, Color.Blue))
}

