package com.example.reversifriend.game

object ReversiEngine {
    private val directions = listOf(
        -1 to -1,
        -1 to 0,
        -1 to 1,
        0 to -1,
        0 to 1,
        1 to -1,
        1 to 0,
        1 to 1,
    )

    fun initialBoard(): Board {
        var board = Board.empty()
        board = board.set(Position(3, 3), Cell.White)
        board = board.set(Position(3, 4), Cell.Black)
        board = board.set(Position(4, 3), Cell.Black)
        board = board.set(Position(4, 4), Cell.White)
        return board
    }

    fun legalMoves(board: Board, player: Player): Set<Position> =
        (0 until Board.BOARD_SIZE).flatMap { row ->
            (0 until Board.BOARD_SIZE).mapNotNull { col ->
                val position = Position(row, col)
                if (flipsForMove(board, position, player).isNotEmpty()) position else null
            }
        }.toSet()

    fun flipsForMove(board: Board, position: Position, player: Player): List<Position> {
        if (!isInside(position.row, position.col) || board[position] != Cell.Empty) {
            return emptyList()
        }

        return directions.flatMap { (dr, dc) ->
            flipsInDirection(board, position, player, dr, dc)
        }
    }

    fun place(board: Board, position: Position, player: Player): Board? {
        val flips = flipsForMove(board, position, player)
        if (flips.isEmpty()) return null

        var next = board.set(position, player.cell)
        flips.forEach { next = next.set(it, player.cell) }
        return next
    }

    fun hasLegalMove(board: Board, player: Player): Boolean =
        legalMoves(board, player).isNotEmpty()

    fun score(board: Board): Score =
        Score(
            black = board.count(Cell.Black),
            white = board.count(Cell.White),
        )

    private fun flipsInDirection(
        board: Board,
        position: Position,
        player: Player,
        dr: Int,
        dc: Int,
    ): List<Position> {
        val captured = mutableListOf<Position>()
        var row = position.row + dr
        var col = position.col + dc

        while (isInside(row, col)) {
            val current = board[row, col]
            when {
                current == player.opponent.cell -> captured += Position(row, col)
                current == player.cell -> return if (captured.isNotEmpty()) captured else emptyList()
                current == Cell.Empty -> return emptyList()
                else -> return emptyList()
            }
            row += dr
            col += dc
        }

        return emptyList()
    }

    private fun isInside(row: Int, col: Int): Boolean =
        row in 0 until Board.BOARD_SIZE && col in 0 until Board.BOARD_SIZE
}

data class Score(val black: Int, val white: Int)
