package com.example.reversifriend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.reversifriend.game.Board
import com.example.reversifriend.game.Cell
import com.example.reversifriend.game.Position
import com.example.reversifriend.ui.theme.ReversiDesign

@Composable
fun ReversiBoard(
    board: Board,
    legalMoves: Set<Position>,
    lastMove: Position?,
    maxBoardSize: Dp = 420.dp,
    enabled: Boolean,
    onCellClick: (Position) -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        val boardSize = if (maxWidth < maxBoardSize) maxWidth else maxBoardSize
        Box(
            modifier = Modifier
                .size(boardSize)
                .background(
                    color = ReversiDesign.SoftSurface.copy(alpha = 0.64f),
                    shape = RoundedCornerShape(16.dp),
                )
                .padding(6.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
                    .background(ReversiDesign.BoardSurface)
                    .border(2.dp, ReversiDesign.BoardBorder, RoundedCornerShape(12.dp)),
            ) {
                repeat(Board.BOARD_SIZE) { row ->
                    Row(modifier = Modifier.weight(1f)) {
                        repeat(Board.BOARD_SIZE) { col ->
                            val position = Position(row, col)
                            BoardCell(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                cell = board[position],
                                isLegalMove = position in legalMoves,
                                isLastMove = position == lastMove,
                                enabled = enabled,
                                onClick = { onCellClick(position) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BoardCell(
    modifier: Modifier,
    cell: Cell,
    isLegalMove: Boolean,
    isLastMove: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .border(0.5.dp, ReversiDesign.BoardLine)
            .background(if (isLastMove) ReversiDesign.BoardLastMove else Color.Transparent)
            .clickable(enabled = enabled && isLegalMove, onClick = onClick)
            .padding(5.dp),
        contentAlignment = Alignment.Center,
    ) {
        when (cell) {
            Cell.Black -> Stone(color = ReversiDesign.BlackStone, border = Color(0xFF0A0F0E))
            Cell.White -> Stone(color = ReversiDesign.WhiteStone, border = Color(0xFFE4DED2))
            Cell.Empty -> if (isLegalMove) LegalMoveHint()
        }
    }
}

@Composable
private fun Stone(color: Color, border: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize(0.78f)
            .aspectRatio(1f)
            .shadow(2.dp, CircleShape)
            .clip(CircleShape)
            .background(color)
            .border(1.dp, border, CircleShape),
    )
}

@Composable
private fun LegalMoveHint() {
    Box(
        modifier = Modifier
            .size(14.dp)
            .clip(CircleShape)
            .background(ReversiDesign.LegalMove.copy(alpha = 0.9f)),
    )
}
