package com.example.reversifriend.game

enum class Cell {
    Empty,
    Black,
    White;

    fun owner(): Player? = when (this) {
        Black -> Player.BLACK
        White -> Player.WHITE
        Empty -> null
    }
}

