package com.example.reversifriend.game

data class GameState(
    val board: Board,
    val currentPlayer: Player,
    val userPlayer: Player,
    val cpuDifficulty: CpuDifficulty,
    val userPet: PetCharacter,
    val message: String,
    val gameOver: Boolean = false,
    val winner: Player? = null,
    val result: GameResult? = null,
    val lastMove: Position? = null,
) {
    val cpuPlayer: Player
        get() = userPlayer.opponent

    val opponentPet: PetCharacter
        get() = userPet.opponent

    val legalMoves: Set<Position>
        get() = if (gameOver) emptySet() else ReversiEngine.legalMoves(board, currentPlayer)

    val userLegalMoves: Set<Position>
        get() = if (!gameOver && currentPlayer == userPlayer) legalMoves else emptySet()

    val score: Score
        get() = ReversiEngine.score(board)

    companion object {
        fun newGame(
            userPlayer: Player,
            cpuDifficulty: CpuDifficulty,
            userPet: PetCharacter,
        ): GameState =
            GameState(
                board = ReversiEngine.initialBoard(),
                currentPlayer = Player.BLACK,
                userPlayer = userPlayer,
                cpuDifficulty = cpuDifficulty,
                userPet = userPet,
                message = "黒からスタート",
            )
    }
}
