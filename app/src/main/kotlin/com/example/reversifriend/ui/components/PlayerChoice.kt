package com.example.reversifriend.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.reversifriend.game.Player
import com.example.reversifriend.ui.theme.ReversiDesign

@Composable
fun PlayerChoice(
    selected: Player,
    onSelected: (Player) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        ChoiceButton(
            modifier = Modifier.weight(1f),
            selected = selected == Player.BLACK,
            text = "黒で開始",
            onClick = { onSelected(Player.BLACK) },
        )
        ChoiceButton(
            modifier = Modifier.weight(1f),
            selected = selected == Player.WHITE,
            text = "白で開始",
            onClick = { onSelected(Player.WHITE) },
        )
    }
}

@Composable
private fun ChoiceButton(
    modifier: Modifier,
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
) {
    if (selected) {
        Button(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = ReversiDesign.Primary,
                contentColor = Color.White,
            ),
            onClick = onClick,
        ) {
            Text(text)
        }
    } else {
        OutlinedButton(
            modifier = modifier,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White.copy(alpha = 0.72f),
                contentColor = ReversiDesign.PrimaryDark,
            ),
            onClick = onClick,
        ) {
            Text(text)
        }
    }
}
