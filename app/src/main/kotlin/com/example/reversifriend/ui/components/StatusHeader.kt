package com.example.reversifriend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.reversifriend.game.GameState
import com.example.reversifriend.ui.theme.ReversiDesign

@Composable
fun StatusHeader(gameState: GameState) {
    val score = gameState.score
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = ReversiDesign.SoftSurface.copy(alpha = 0.74f),
                    shape = RoundedCornerShape(14.dp),
                )
                .padding(horizontal = 12.dp, vertical = 8.dp),
        ) {
            Text(
                text = "Reversi Friend",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = ReversiDesign.PrimaryDark,
                maxLines = 1,
            )
            Text(
                text = "リカとマイのリバーシ対局",
                style = MaterialTheme.typography.bodySmall,
                color = ReversiDesign.TextMuted,
                maxLines = 1,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            StatusChip(
                modifier = Modifier.weight(1f),
                label = "黒",
                value = "${score.black}",
                backgroundColor = ReversiDesign.GameInfoSection,
            )
            StatusChip(
                modifier = Modifier.weight(1f),
                label = "白",
                value = "${score.white}",
                backgroundColor = ReversiDesign.SoftSurface,
            )
            StatusChip(
                modifier = Modifier.weight(1f),
                label = "あなた",
                value = gameState.userPlayer.displayName,
                backgroundColor = ReversiDesign.ColorSection,
            )
            StatusChip(
                modifier = Modifier.weight(1f),
                label = "強さ",
                value = gameState.cpuDifficulty.displayName,
                backgroundColor = ReversiDesign.CpuSection,
            )
        }
    }
}

@Composable
private fun StatusChip(
    modifier: Modifier,
    label: String,
    value: String,
    backgroundColor: androidx.compose.ui.graphics.Color,
) {
    Column(
        modifier = modifier
            .background(
                color = backgroundColor.copy(alpha = 0.94f),
                shape = RoundedCornerShape(12.dp),
            )
            .padding(horizontal = 8.dp, vertical = 7.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = ReversiDesign.TextMuted,
            maxLines = 1,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = ReversiDesign.TextStrong,
            maxLines = 1,
        )
    }
}
