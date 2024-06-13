package com.example.masterand.GameScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SelectableColorsRow(
    colors: List<Color>,
    onClick: (buttonIndex: Int) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        for (i in colors.indices) {
            CircularButton(onClick = { onClick(i) }, color = colors[i])
        }
    }
}

@Preview
@Composable
fun SelectableColorsRowPreview() {
    SelectableColorsRow(
        colors = listOf(Color.Red, Color.Cyan, Color.Green, Color.Blue),
        onClick = {}
    )
}