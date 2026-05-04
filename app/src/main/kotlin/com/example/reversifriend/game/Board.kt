package com.example.reversifriend.game

data class Board(val cells: List<Cell>) {
    init {
        require(cells.size == BOARD_SIZE * BOARD_SIZE)
    }

    operator fun get(position: Position): Cell = cells[index(position.row, position.col)]

    operator fun get(row: Int, col: Int): Cell = cells[index(row, col)]

    fun set(position: Position, cell: Cell): Board {
        val next = cells.toMutableList()
        next[index(position.row, position.col)] = cell
        return copy(cells = next)
    }

    fun count(cell: Cell): Int = cells.count { it == cell }

    companion object {
        const val BOARD_SIZE = 8

        fun empty(): Board = Board(List(BOARD_SIZE * BOARD_SIZE) { Cell.Empty })

        private fun index(row: Int, col: Int): Int = row * BOARD_SIZE + col
    }
}

