package com.example.reversifriend.game

enum class PetCharacter(val displayName: String) {
    RIKA("リカ"),
    MAI("マイ");

    val opponent: PetCharacter
        get() = if (this == RIKA) MAI else RIKA
}
