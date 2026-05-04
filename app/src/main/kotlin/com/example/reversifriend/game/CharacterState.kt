package com.example.reversifriend.game

enum class CharacterState(val displayName: String, val line: String) {
    NORMAL("通常", "いい勝負だね"),
    ADVANTAGE("優勢", "このまま押し切るよ"),
    DISADVANTAGE("劣勢", "まだ逆転できるはず…"),
    WIN("勝利", "やった、私の勝ち！"),
    LOSE("敗北", "負けちゃった…もう一回！"),
    DRAW("引き分け", "引き分けだね。いい勝負だったよ");

    companion object {
        fun fromGame(state: GameState): CharacterState {
            if (state.gameOver) {
                return when (state.result) {
                    GameResult.CPU_WIN -> WIN
                    GameResult.USER_WIN -> LOSE
                    GameResult.DRAW -> DRAW
                    null -> NORMAL
                }
            }

            val score = state.score
            val cpuCount = if (state.cpuPlayer == Player.BLACK) score.black else score.white
            val userCount = if (state.userPlayer == Player.BLACK) score.black else score.white
            val diff = cpuCount - userCount

            return when {
                diff >= 5 -> ADVANTAGE
                diff <= -5 -> DISADVANTAGE
                else -> NORMAL
            }
        }
    }
}

