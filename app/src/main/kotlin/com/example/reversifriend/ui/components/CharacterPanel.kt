package com.example.reversifriend.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.reversifriend.R
import com.example.reversifriend.game.BattlePerspective
import com.example.reversifriend.game.CharacterState
import com.example.reversifriend.game.GameResult
import com.example.reversifriend.game.GameState
import com.example.reversifriend.game.PetCharacter
import com.example.reversifriend.game.Player
import com.example.reversifriend.game.getBattleStateMessage
import com.example.reversifriend.game.getOpeningMessage
import com.example.reversifriend.ui.theme.ReversiDesign

@Composable
fun CharacterPanel(gameState: GameState) {
    val playerState = CharacterState.fromPlayerSide(gameState)
    val opponentState = CharacterState.fromGame(gameState)
    val conversation = remember(
        gameState.board,
        gameState.currentPlayer,
        gameState.gameOver,
        gameState.result,
        gameState.userPet,
        gameState.userPlayer,
    ) {
        buildBattleConversation(gameState)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(
                color = ReversiDesign.ConversationSection.copy(alpha = 0.94f),
            )
            .padding(horizontal = 8.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PetPortrait(
            name = gameState.userPet.displayName,
            role = "あなた",
            state = playerState,
            pet = gameState.userPet,
        )
        ConversationColumn(
            modifier = Modifier.weight(1f),
            conversation = conversation,
        )
        PetPortrait(
            name = gameState.opponentPet.displayName,
            role = "対戦相手",
            state = opponentState,
            pet = gameState.opponentPet,
        )
    }
}

@Composable
private fun PetPortrait(
    name: String,
    role: String,
    state: CharacterState,
    pet: PetCharacter,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = role,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
        )
        CharacterAvatar(state = state, pet = pet)
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
        )
    }
}

@Composable
private fun ConversationColumn(
    modifier: Modifier,
    conversation: BattleConversation,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        ChatBubble(
            speaker = conversation.playerName,
            message = conversation.playerLine,
            pet = conversation.playerPet,
            textAlign = TextAlign.Start,
        )
        ChatBubble(
            speaker = conversation.opponentName,
            message = conversation.opponentLine,
            pet = conversation.opponentPet,
            textAlign = TextAlign.End,
        )
    }
}

@Composable
private fun ChatBubble(
    speaker: String,
    message: String,
    pet: PetCharacter,
    textAlign: TextAlign,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = pet.messageBackground().copy(alpha = 0.96f),
                shape = RoundedCornerShape(10.dp),
            )
            .padding(horizontal = 8.dp, vertical = 5.dp),
    ) {
        Text(
            text = speaker,
            style = MaterialTheme.typography.labelSmall,
            color = pet.nameColor(),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = textAlign,
            maxLines = 1,
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(),
            textAlign = textAlign,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun CharacterAvatar(
    state: CharacterState,
    pet: PetCharacter,
) {
    val imageRes = when (pet) {
        PetCharacter.RIKA -> when (state) {
            CharacterState.NORMAL -> R.drawable.cpu_rika_normal
            CharacterState.ADVANTAGE -> R.drawable.cpu_rika_advantage
            CharacterState.DISADVANTAGE -> R.drawable.cpu_rika_disadvantage
            CharacterState.WIN -> R.drawable.cpu_rika_win
            CharacterState.LOSE -> R.drawable.cpu_rika_lose
            CharacterState.DRAW -> R.drawable.cpu_rika_draw
        }
        PetCharacter.MAI -> when (state) {
            CharacterState.NORMAL -> R.drawable.opponent_mai_normal
            CharacterState.ADVANTAGE -> R.drawable.opponent_mai_advantage
            CharacterState.DISADVANTAGE -> R.drawable.opponent_mai_disadvantage
            CharacterState.WIN -> R.drawable.opponent_mai_win
            CharacterState.LOSE -> R.drawable.opponent_mai_lose
            CharacterState.DRAW -> R.drawable.opponent_mai_draw
        }
    }
    Box(
        modifier = Modifier
            .size(width = 56.dp, height = 72.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(pet.portraitBackground().copy(alpha = 0.58f)),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            contentScale = ContentScale.Fit,
        )
    }
}

private data class BattleConversation(
    val playerName: String,
    val playerPet: PetCharacter,
    val playerLine: String,
    val opponentName: String,
    val opponentPet: PetCharacter,
    val opponentLine: String,
)

private fun CharacterState.Companion.fromPlayerSide(state: GameState): CharacterState {
    if (state.gameOver) {
        return when (state.result) {
            GameResult.USER_WIN -> CharacterState.WIN
            GameResult.CPU_WIN -> CharacterState.LOSE
            GameResult.DRAW -> CharacterState.DRAW
            null -> CharacterState.NORMAL
        }
    }

    val score = state.score
    val playerCount = if (state.userPlayer == Player.BLACK) score.black else score.white
    val opponentCount = if (state.cpuPlayer == Player.BLACK) score.black else score.white
    val diff = playerCount - opponentCount

    return when {
        diff >= 5 -> CharacterState.ADVANTAGE
        diff <= -5 -> CharacterState.DISADVANTAGE
        else -> CharacterState.NORMAL
    }
}

private fun buildBattleConversation(state: GameState): BattleConversation {
    val playerName = state.userPet.displayName
    val opponentName = state.opponentPet.displayName
    val score = state.score
    val isOpening = !state.gameOver && state.lastMove == null

    return BattleConversation(
        playerName = playerName,
        playerPet = state.userPet,
        playerLine = if (isOpening) {
            getOpeningMessage(state.userPet)
        } else {
            getBattleStateMessage(
                characterId = state.userPet,
                perspective = BattlePerspective.SELF,
                blackCount = score.black,
                whiteCount = score.white,
                playerColor = state.userPlayer,
                isGameOver = state.gameOver,
            )
        },
        opponentName = opponentName,
        opponentPet = state.opponentPet,
        opponentLine = if (isOpening) {
            getOpeningMessage(state.opponentPet)
        } else {
            getBattleStateMessage(
                characterId = state.opponentPet,
                perspective = BattlePerspective.OPPONENT,
                blackCount = score.black,
                whiteCount = score.white,
                playerColor = state.userPlayer,
                isGameOver = state.gameOver,
            )
        },
    )
}

private fun PetCharacter.messageBackground(): Color =
    when (this) {
        PetCharacter.RIKA -> ReversiDesign.RikaMessage
        PetCharacter.MAI -> ReversiDesign.MaiMessage
    }

private fun PetCharacter.portraitBackground(): Color =
    when (this) {
        PetCharacter.RIKA -> ReversiDesign.RikaSelected
        PetCharacter.MAI -> ReversiDesign.MaiSelected
    }

private fun PetCharacter.nameColor(): Color =
    when (this) {
        PetCharacter.RIKA -> ReversiDesign.PrimaryDark
        PetCharacter.MAI -> Color(0xFF5A4B86)
    }
