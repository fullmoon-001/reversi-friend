package com.example.reversifriend.game

enum class Player(val cell: Cell, val displayName: String) {
    BLACK(Cell.Black, "黒"),
    WHITE(Cell.White, "白");

    val opponent: Player
        get() = if (this == BLACK) WHITE else BLACK
}

