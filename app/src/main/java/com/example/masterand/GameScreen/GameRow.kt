package com.example.masterand.GameScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GameRow(
    choosenColors: List<Color>,
    feedbackColors: List<Color>,
    clickable: Boolean,
    onSelectColorClick: (Int) -> Unit,
    onCheckClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        SelectableColorsRow(colors = choosenColors, onClick = onSelectColorClick)

        IconButton(
            onClick = {
                onCheckClick()
            },
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
                .background(color = MaterialTheme.colorScheme.background),
            colors = IconButtonDefaults.filledIconButtonColors(),
            enabled = clickable
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Confirm",
                tint = Color.White,
            )
        }

        FeedbackCircles(colors = feedbackColors)
    }
}
