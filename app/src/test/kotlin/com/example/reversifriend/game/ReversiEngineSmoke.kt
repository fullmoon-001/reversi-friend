package com.example.reversifriend.game

fun main() {
    val board = ReversiEngine.initialBoard()
    check(ReversiEngine.legalMoves(board, Player.BLACK).size == 4)
    check(ReversiEngine.legalMoves(board, Player.WHITE).size == 4)

    val afterUserMove = GameActions.playUserMove(
        state = GameActions.start(Player.BLACK),
        position = Position(2, 3),
    )
    check(afterUserMove.score.black == 4)
    check(afterUserMove.score.white == 1)
    check(afterUserMove.currentPlayer == Player.WHITE)

    val afterCpuStart = GameActions.playCpuMove(GameActions.start(Player.WHITE))
    check(afterCpuStart.score.black == 4)
    check(afterCpuStart.score.white == 1)
    check(afterCpuStart.currentPlayer == Player.WHITE)

    println("logic smoke test passed")
}

