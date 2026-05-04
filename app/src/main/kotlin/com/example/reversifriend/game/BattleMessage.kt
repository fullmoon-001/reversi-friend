package com.example.reversifriend.game

import kotlin.random.Random

enum class BattlePerspective {
    SELF,
    OPPONENT,
}

private enum class BattleMessageState {
    VERY_ADVANTAGE,
    SLIGHT_ADVANTAGE,
    EVEN,
    SLIGHT_DISADVANTAGE,
    VERY_DISADVANTAGE,
    WIN,
    LOSE,
    DRAW,
}

fun getBattleStateMessage(
    characterId: PetCharacter,
    perspective: BattlePerspective,
    blackCount: Int,
    whiteCount: Int,
    playerColor: Player,
    isGameOver: Boolean,
): String {
    val ownColor = when (perspective) {
        BattlePerspective.SELF -> playerColor
        BattlePerspective.OPPONENT -> playerColor.opponent
    }
    val ownCount = if (ownColor == Player.BLACK) blackCount else whiteCount
    val opponentCount = if (ownColor == Player.BLACK) whiteCount else blackCount
    val diff = ownCount - opponentCount
    val state = when {
        isGameOver && diff > 0 -> BattleMessageState.WIN
        isGameOver && diff < 0 -> BattleMessageState.LOSE
        isGameOver -> BattleMessageState.DRAW
        diff >= 15 -> BattleMessageState.VERY_ADVANTAGE
        diff >= 5 -> BattleMessageState.SLIGHT_ADVANTAGE
        diff <= -15 -> BattleMessageState.VERY_DISADVANTAGE
        diff <= -5 -> BattleMessageState.SLIGHT_DISADVANTAGE
        else -> BattleMessageState.EVEN
    }

    return messagesFor(characterId, state).random()
}

fun getOpeningMessage(characterId: PetCharacter): String =
    when (characterId) {
        PetCharacter.RIKA -> listOf(
            "よろしくね！楽しく勝負しよ！",
            "よーし、がんばるよ！よろしく！",
            "よろしくお願いします！いい勝負にしようね！",
        )
        PetCharacter.MAI -> listOf(
            "よろしくお願いいたします。どうぞお手柔らかに",
            "ふふ、よろしくお願いします。素敵な一局にしましょう",
            "よろしくお願いします。最後まで楽しませてくださいね",
        )
    }.random()

private fun messagesFor(
    characterId: PetCharacter,
    state: BattleMessageState,
): List<String> =
    when (characterId) {
        PetCharacter.RIKA -> rikaMessages(state)
        PetCharacter.MAI -> maiMessages(state)
    }

private fun rikaMessages(state: BattleMessageState): List<String> =
    when (state) {
        BattleMessageState.VERY_ADVANTAGE -> listOf(
            "このままいけそう！でも油断しないよ！",
            "よしよし、いい感じ！",
            "あと少しで勝てそう！",
        )
        BattleMessageState.SLIGHT_ADVANTAGE -> listOf(
            "ちょっとリードしてるかも！",
            "いい流れきてる！",
            "この調子でがんばるよ！",
        )
        BattleMessageState.EVEN -> listOf(
            "まだまだわからないね！",
            "ここから勝負だよ！",
            "いい勝負だね！",
        )
        BattleMessageState.SLIGHT_DISADVANTAGE -> listOf(
            "ちょっとまずいかも…でもまだいける！",
            "ここから巻き返すよ！",
            "あきらめないからね！",
        )
        BattleMessageState.VERY_DISADVANTAGE -> listOf(
            "うわー、ピンチかも！",
            "でも最後までがんばる！",
            "逆転したい！",
        )
        BattleMessageState.WIN -> listOf(
            "やったー！勝てた！",
            "いい勝負だったね！",
            "今日は調子よかった！",
        )
        BattleMessageState.LOSE -> listOf(
            "負けちゃった…次は勝つ！",
            "くやしいー！もう一回！",
            "次は絶対勝つよ！",
        )
        BattleMessageState.DRAW -> listOf(
            "引き分けだね！いい勝負！",
            "お互いがんばったね！",
            "もう一回やろ！",
        )
    }

private fun maiMessages(state: BattleMessageState): List<String> =
    when (state) {
        BattleMessageState.VERY_ADVANTAGE -> listOf(
            "ふふ…ここまで差がつくと、さすがに見ていて可哀想ですね",
            "まだ続けますか？結果はもう変わらないと思いますが",
            "ここまで来ると、少し相手を選びたくなりますね",
        )
        BattleMessageState.SLIGHT_ADVANTAGE -> listOf(
            "少しずつ差が開いていますね。気づいていらっしゃいます？",
            "無理に攻めなくても、自然に差がついていきますね",
            "焦っているのが手に取るようにわかります",
        )
        BattleMessageState.EVEN -> listOf(
            "ふふ…ここまでは悪くありませんね。ここまでは",
            "意外と粘りますね。でも、そろそろ崩れそうです",
            "その手、本気で打ってます？",
        )
        BattleMessageState.SLIGHT_DISADVANTAGE -> listOf(
            "あら…少し調子に乗らせてしまいましたね",
            "その程度で優勢気分ですか？かわいらしいですね",
            "まあいいでしょう、少し遊んで差し上げます",
        )
        BattleMessageState.VERY_DISADVANTAGE -> listOf(
            "ふふ…ここで喜んでいるなら、先が思いやられますね",
            "ずいぶん楽しそうですね。その顔、あとで変わりますよ",
            "今だけは優越感に浸っていてください",
        )
        BattleMessageState.WIN -> listOf(
            "お疲れさまでした。これが実力差というものです",
            "ふふ、もう少し楽しめる相手だと思っていました",
            "悪くありませんでしたよ。期待しすぎていた私が悪かったですね",
        )
        BattleMessageState.LOSE -> listOf(
            "あら…今回はたまたまですね。本当に運が良い",
            "ふふ、いい気分でしょう？その時間、大事にしてくださいね",
            "今回は認めて差し上げます。ただし次はありません",
        )
        BattleMessageState.DRAW -> listOf(
            "引き分けですか。少しだけ期待外れでした",
            "決めきれないあたりが、らしいですね",
            "まあ…これ以上続けても同じでしょう",
        )
    }

private fun <T> List<T>.random(): T = this[Random.nextInt(size)]
