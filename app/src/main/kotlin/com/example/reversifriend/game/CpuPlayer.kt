package com.example.reversifriend.game

import kotlin.random.Random

object CpuPlayer {
    private val corners = setOf(
        Position(0, 0),
        Position(0, 7),
        Position(7, 0),
        Position(7, 7),
    )
    private val nearCornerRisk = setOf(
        Position(0, 1),
        Position(1, 0),
        Position(1, 1),
        Position(0, 6),
        Position(1, 6),
        Position(1, 7),
        Position(6, 0),
        Position(6, 1),
        Position(7, 1),
        Position(6, 6),
        Position(6, 7),
        Position(7, 6),
    )

    fun chooseMove(
        board: Board,
        player: Player,
        difficulty: CpuDifficulty,
    ): Position? {
        val moves = ReversiEngine.legalMoves(board, player).toList()
        if (moves.isEmpty()) return null

        return when (difficulty) {
            CpuDifficulty.EASY -> moves.random()
            CpuDifficulty.NORMAL -> chooseNormalMove(moves)
            CpuDifficulty.HARD -> chooseHardMove(board, player, moves)
        }
    }

    private fun chooseNormalMove(moves: List<Position>): Position {
        val cornerMoves = moves.filter { it in corners }
        if (cornerMoves.isNotEmpty()) return cornerMoves.random()

        return moves.random()
    }

    private fun chooseHardMove(
        board: Board,
        player: Player,
        moves: List<Position>,
    ): Position =
        moves.maxBy { move -> scoreMove(board, player, move) + Random.nextInt(4) }

    private fun scoreMove(
        board: Board,
        player: Player,
        move: Position,
    ): Int {
        val flips = ReversiEngine.flipsForMove(board, move, player).size
        val nextBoard = ReversiEngine.place(board, move, player)

        var score = flips * 8
        if (move in corners) score += 120
        if (move.isEdge()) score += 18
        if (move in nearCornerRisk && corners.any { board[it] == Cell.Empty }) score -= 35

        if (nextBoard != null) {
            score -= ReversiEngine.legalMoves(nextBoard, player.opponent).size * 3
            score += ReversiEngine.legalMoves(nextBoard, player).size
        }

        return score
    }

    private fun Position.isEdge(): Boolean =
        row == 0 || row == Board.BOARD_SIZE - 1 || col == 0 || col == Board.BOARD_SIZE - 1

    private fun <T> List<T>.random(): T = this[Random.nextInt(size)]
}
