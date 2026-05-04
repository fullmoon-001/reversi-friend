package com.example.reversifriend.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.reversifriend.R
import com.example.reversifriend.game.CpuDifficulty
import com.example.reversifriend.game.GameActions
import com.example.reversifriend.game.GameState
import com.example.reversifriend.game.PetCharacter
import com.example.reversifriend.game.Player
import com.example.reversifriend.ui.components.CharacterPanel
import com.example.reversifriend.ui.components.GameResultOverlay
import com.example.reversifriend.ui.components.PlayerChoice
import com.example.reversifriend.ui.components.ReversiBoard
import com.example.reversifriend.ui.components.StatusHeader
import com.example.reversifriend.ui.theme.ReversiDesign
import kotlinx.coroutines.delay

@Composable
fun ReversiFriendApp() {
    var selectedPlayer by remember { mutableStateOf(Player.BLACK) }
    var selectedDifficulty by remember { mutableStateOf(CpuDifficulty.NORMAL) }
    var selectedPet by remember { mutableStateOf(PetCharacter.RIKA) }
    var gameState by remember { mutableStateOf<GameState?>(null) }
    var showResultOverlay by remember { mutableStateOf(false) }
    val currentGameState = gameState

    LaunchedEffect(currentGameState?.board, currentGameState?.currentPlayer, currentGameState?.gameOver) {
        val state = currentGameState ?: return@LaunchedEffect
        if (!state.gameOver && state.currentPlayer == state.cpuPlayer) {
            delay(450)
            gameState = GameActions.playCpuMove(state)
        }
    }

    LaunchedEffect(currentGameState?.result, currentGameState?.gameOver) {
        showResultOverlay = currentGameState?.let { it.gameOver && it.result != null } == true
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        if (currentGameState == null) {
            StartMenu(
                selectedPlayer = selectedPlayer,
                onPlayerSelected = { selectedPlayer = it },
                selectedDifficulty = selectedDifficulty,
                onDifficultySelected = { selectedDifficulty = it },
                selectedPet = selectedPet,
                onPetSelected = { selectedPet = it },
                onStart = {
                    gameState = GameActions.start(
                        userPlayer = selectedPlayer,
                        cpuDifficulty = selectedDifficulty,
                        userPet = selectedPet,
                    )
                    showResultOverlay = false
                },
            )
        } else {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ReversiDesign.screenBrush())
                    .statusBarsPadding()
                    .navigationBarsPadding(),
            ) {
                SoftBackgroundSparkles()
                val boardMaxSize = when {
                    maxHeight < 720.dp -> 286.dp
                    maxHeight < 800.dp -> 304.dp
                    else -> 318.dp
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    StatusHeader(gameState = currentGameState)
                    ReversiBoard(
                        board = currentGameState.board,
                        legalMoves = currentGameState.userLegalMoves,
                        lastMove = currentGameState.lastMove,
                        maxBoardSize = boardMaxSize,
                        enabled = !currentGameState.gameOver && currentGameState.currentPlayer == currentGameState.userPlayer,
                        onCellClick = { gameState = GameActions.playUserMove(currentGameState, it) },
                    )
                    CurrentMessage(gameState = currentGameState)
                    CharacterPanel(gameState = currentGameState)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ReversiDesign.Primary,
                                contentColor = Color.White,
                            ),
                            onClick = {
                                gameState = GameActions.start(
                                    userPlayer = currentGameState.userPlayer,
                                    cpuDifficulty = currentGameState.cpuDifficulty,
                                    userPet = currentGameState.userPet,
                                )
                            },
                        ) {
                            Text("リスタート")
                        }
                        OutlinedButton(
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.White.copy(alpha = 0.72f),
                                contentColor = ReversiDesign.PrimaryDark,
                            ),
                            onClick = {
                                selectedPlayer = currentGameState.userPlayer
                                selectedDifficulty = currentGameState.cpuDifficulty
                                selectedPet = currentGameState.userPet
                                showResultOverlay = false
                                gameState = null
                            },
                        ) {
                            Text("メニューへ")
                        }
                    }
                }

                if (showResultOverlay) {
                    GameResultOverlay(
                        gameState = currentGameState,
                        onDismiss = { showResultOverlay = false },
                        onRestart = {
                            gameState = GameActions.start(
                                userPlayer = currentGameState.userPlayer,
                                cpuDifficulty = currentGameState.cpuDifficulty,
                                userPet = currentGameState.userPet,
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun StartMenu(
    selectedPlayer: Player,
    onPlayerSelected: (Player) -> Unit,
    selectedDifficulty: CpuDifficulty,
    onDifficultySelected: (CpuDifficulty) -> Unit,
    selectedPet: PetCharacter,
    onPetSelected: (PetCharacter) -> Unit,
    onStart: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ReversiDesign.screenBrush())
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        SoftBackgroundSparkles()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            MenuTitle()
            Spacer(modifier = Modifier.height(16.dp))

            MenuSection(
                title = "CPUの強さ",
                backgroundColor = ReversiDesign.CpuSection,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    CpuDifficulty.entries.forEach { difficulty ->
                        SelectionButton(
                            modifier = Modifier.weight(1f),
                            selected = selectedDifficulty == difficulty,
                            text = difficulty.displayName,
                            onClick = { onDifficultySelected(difficulty) },
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(14.dp))

            MenuSection(
                title = "自分の色",
                backgroundColor = ReversiDesign.ColorSection,
            ) {
                PlayerChoice(
                    selected = selectedPlayer,
                    onSelected = onPlayerSelected,
                )
            }
            Spacer(modifier = Modifier.height(14.dp))

            MenuSection(
                title = "自分のキャラ",
                backgroundColor = ReversiDesign.CharacterSection,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    CharacterChoiceButton(
                        modifier = Modifier.weight(1f),
                        pet = PetCharacter.RIKA,
                        selected = selectedPet == PetCharacter.RIKA,
                        onClick = { onPetSelected(PetCharacter.RIKA) },
                    )
                    CharacterChoiceButton(
                        modifier = Modifier.weight(1f),
                        pet = PetCharacter.MAI,
                        selected = selectedPet == PetCharacter.MAI,
                        onClick = { onPetSelected(PetCharacter.MAI) },
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                .height(54.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ReversiDesign.Primary,
                    contentColor = Color.White,
                ),
                onClick = onStart,
            ) {
                Text("対局スタート")
            }
        }
    }
}

@Composable
private fun SoftBackgroundSparkles() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val sparkles = listOf(
            Sparkle(0.08f, 0.07f, 3.0f, 0.44f),
            Sparkle(0.34f, 0.12f, 2.4f, 0.34f),
            Sparkle(0.63f, 0.08f, 2.8f, 0.38f),
            Sparkle(0.89f, 0.10f, 3.2f, 0.36f),
            Sparkle(0.16f, 0.18f, 2.2f, 0.28f),
            Sparkle(0.74f, 0.18f, 2.6f, 0.30f),
        )

        sparkles.forEach { sparkle ->
            val center = Offset(size.width * sparkle.x, size.height * sparkle.y)
            val arm = sparkle.armDp.dp.toPx()
            val color = Color.White.copy(alpha = sparkle.alpha)
            drawLine(
                color = color,
                start = Offset(center.x - arm, center.y),
                end = Offset(center.x + arm, center.y),
                strokeWidth = 1.dp.toPx(),
            )
            drawLine(
                color = color,
                start = Offset(center.x, center.y - arm),
                end = Offset(center.x, center.y + arm),
                strokeWidth = 1.dp.toPx(),
            )
            drawCircle(
                color = color.copy(alpha = sparkle.alpha * 0.72f),
                radius = arm * 0.24f,
                center = center,
            )
        }
    }
}

private data class Sparkle(
    val x: Float,
    val y: Float,
    val armDp: Float,
    val alpha: Float,
)

@Composable
private fun MenuTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = ReversiDesign.SoftSurface.copy(alpha = 0.72f),
                shape = RoundedCornerShape(16.dp),
            )
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.cpu_rika_normal),
            contentDescription = null,
            modifier = Modifier.size(width = 48.dp, height = 60.dp),
            contentScale = ContentScale.Fit,
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Reversi Friend",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = ReversiDesign.PrimaryDark,
            )
            Text(
                text = "リカとマイのリバーシ対局",
                style = MaterialTheme.typography.bodyMedium,
                color = ReversiDesign.TextMuted,
                maxLines = 1,
            )
        }
        Image(
            painter = painterResource(id = R.drawable.opponent_mai_normal),
            contentDescription = null,
            modifier = Modifier.size(width = 48.dp, height = 60.dp),
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
private fun MenuSection(
    title: String,
    backgroundColor: Color,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor.copy(alpha = 0.94f),
                shape = RoundedCornerShape(14.dp),
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = ReversiDesign.TextStrong,
        )
        content()
    }
}

@Composable
private fun SelectionButton(
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

@Composable
private fun CharacterChoiceButton(
    modifier: Modifier,
    pet: PetCharacter,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val content: @Composable () -> Unit = {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Image(
                painter = painterResource(id = pet.normalImageRes()),
                contentDescription = pet.displayName,
                modifier = Modifier.size(width = 64.dp, height = 80.dp),
                contentScale = ContentScale.Fit,
            )
            Text(pet.displayName, maxLines = 1)
        }
    }

    if (selected) {
        Button(
            modifier = modifier.height(126.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = pet.selectedColor(),
                contentColor = ReversiDesign.TextStrong,
            ),
            onClick = onClick,
        ) {
            content()
        }
    } else {
        OutlinedButton(
            modifier = modifier.height(126.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White.copy(alpha = 0.75f),
                contentColor = ReversiDesign.TextStrong,
            ),
            onClick = onClick,
        ) {
            content()
        }
    }
}

private fun PetCharacter.normalImageRes(): Int =
    when (this) {
        PetCharacter.RIKA -> R.drawable.cpu_rika_normal
        PetCharacter.MAI -> R.drawable.opponent_mai_normal
    }

private fun PetCharacter.selectedColor(): Color =
    when (this) {
        PetCharacter.RIKA -> ReversiDesign.RikaSelected
        PetCharacter.MAI -> ReversiDesign.MaiSelected
    }

@Composable
private fun CurrentMessage(gameState: GameState) {
    val turnText = when {
        gameState.gameOver -> "ゲーム終了"
        gameState.currentPlayer == gameState.userPlayer -> "あなたの番"
        else -> "CPUの番"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = ReversiDesign.TurnSection.copy(alpha = 0.92f),
                shape = RoundedCornerShape(12.dp),
            )
            .padding(horizontal = 12.dp, vertical = 7.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = turnText,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = ReversiDesign.TextStrong,
        )
        Text(
            text = gameState.message,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
        )
    }
}
