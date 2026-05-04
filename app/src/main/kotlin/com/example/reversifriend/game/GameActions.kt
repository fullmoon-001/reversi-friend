package com.example.reversifriend.game

object GameActions {
    fun start(
        userPlayer: Player,
        cpuDifficulty: CpuDifficulty = CpuDifficulty.NORMAL,
        userPet: PetCharacter = PetCharacter.RIKA,
    ): GameState = GameState.newGame(
        userPlayer = userPlayer,
        cpuDifficulty = cpuDifficulty,
        userPet = userPet,
    )

    fun playUserMove(state: GameState, position: Position): GameState {
        if (state.gameOver || state.currentPlayer != state.userPlayer) return state
        if (position !in state.userLegalMoves) return state

        return placeAndAdvance(
            state = state,
            position = position,
            player = state.userPlayer,
            moveMessage = "あなたが${position.label()}に置きました",
        )
    }

    fun playCpuMove(state: GameState): GameState {
        if (state.gameOver || state.currentPlayer != state.cpuPlayer) return state

        val move = CpuPlayer.chooseMove(
            board = state.board,
            player = state.cpuPlayer,
            difficulty = state.cpuDifficulty,
        )
            ?: return finishIfNoOneCanMove(state)

        return placeAndAdvance(
            state = state,
            position = move,
            player = state.cpuPlayer,
            moveMessage = "CPUが${move.label()}に置きました",
        )
    }

    private fun placeAndAdvance(
        state: GameState,
        position: Position,
        player: Player,
        moveMessage: String,
    ): GameState {
        val nextBoard = ReversiEngine.place(state.board, position, player) ?: return state
        return advanceTurn(
            state = state.copy(
                board = nextBoard,
                lastMove = position,
                message = moveMessage,
            ),
            mover = player,
        )
    }

    private fun advanceTurn(state: GameState, mover: Player): GameState {
        val nextPlayer = mover.opponent
        val nextHasMove = ReversiEngine.hasLegalMove(state.board, nextPlayer)
        val moverHasMove = ReversiEngine.hasLegalMove(state.board, mover)

        return when {
            nextHasMove -> state.copy(currentPlayer = nextPlayer)
            moverHasMove -> state.copy(
                currentPlayer = mover,
                message = "${state.message}\n${nextPlayer.displayName}は置ける場所がないのでパス",
            )
            else -> finishGame(state)
        }
    }

    private fun finishIfNoOneCanMove(state: GameState): GameState {
        val userCanMove = ReversiEngine.hasLegalMove(state.board, state.userPlayer)
        val cpuCanMove = ReversiEngine.hasLegalMove(state.board, state.cpuPlayer)
        return if (!userCanMove && !cpuCanMove) {
            finishGame(state)
        } else {
            state.copy(
                currentPlayer = state.currentPlayer.opponent,
                message = "${state.currentPlayer.displayName}は置ける場所がないのでパス",
            )
        }
    }

    private fun finishGame(state: GameState): GameState {
        val score = state.score
        val winner = when {
            score.black > score.white -> Player.BLACK
            score.white > score.black -> Player.WHITE
            else -> null
        }
        val result = when (winner) {
            null -> GameResult.DRAW
            state.userPlayer -> GameResult.USER_WIN
            else -> GameResult.CPU_WIN
        }
        val message = when (result) {
            GameResult.USER_WIN -> "対局終了：あなたの勝ち！"
            GameResult.CPU_WIN -> "対局終了：CPUの勝ち"
            GameResult.DRAW -> "対局終了：引き分け"
        }

        return state.copy(
            gameOver = true,
            winner = winner,
            result = result,
            message = message,
        )
    }

    private fun Position.label(): String {
        val rowLabel = row + 1
        val colLabel = ('A'.code + col).toChar()
        return "$colLabel$rowLabel"
    }
}
