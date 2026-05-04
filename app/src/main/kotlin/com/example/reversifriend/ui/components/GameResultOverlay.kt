package com.example.reversifriend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.reversifriend.game.GameResult
import com.example.reversifriend.game.GameState
import com.example.reversifriend.ui.theme.ReversiDesign

@Composable
fun GameResultOverlay(
    gameState: GameState,
    onDismiss: () -> Unit,
    onRestart: () -> Unit,
) {
    val result = gameState.result ?: return
    val score = gameState.score
    val title = when (result) {
        GameResult.USER_WIN -> "勝利!"
        GameResult.CPU_WIN -> "敗北..."
        GameResult.DRAW -> "引き分け"
    }
    val message = when (result) {
        GameResult.USER_WIN -> "あなたの勝ちです"
        GameResult.CPU_WIN -> "CPUの勝ちです"
        GameResult.DRAW -> "いい勝負でした"
    }
    val accentColor = when (result) {
        GameResult.USER_WIN -> ReversiDesign.LegalMove
        GameResult.CPU_WIN -> MaterialTheme.colorScheme.tertiary
        GameResult.DRAW -> ReversiDesign.Primary
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.42f))
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = ReversiDesign.SoftSurface,
                    shape = RoundedCornerShape(16.dp),
                )
                .padding(horizontal = 22.dp, vertical = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium,
                color = accentColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Text(
                text = message,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = "黒 ${score.black} - 白 ${score.white}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White.copy(alpha = 0.72f),
                        contentColor = ReversiDesign.PrimaryDark,
                    ),
                    onClick = onDismiss,
                ) {
                    Text("盤面を見る")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ReversiDesign.Primary,
                        contentColor = Color.White,
                    ),
                    onClick = onRestart,
                ) {
                    Text("もう一回")
                }
            }
        }
    }
}
