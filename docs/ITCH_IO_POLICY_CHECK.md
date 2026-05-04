# itch.io公開前の規約チェック

確認日: 2026-05-04

## 結論

`Reversi Friend` を無料のAndroid APKとしてitch.ioに公開する範囲では、現時点で大きな規約違反になりそうな点は見当たりません。

## 確認した公式情報

- itch.io Terms of Service  
  https://itch.io/docs/legal/terms
- itch.io Creator FAQ  
  https://itch.io/docs/creators/faq
- itch.io Getting Started  
  https://itch.io/docs/creators/getting-started
- itch.io Community rules  
  https://itch.io/docs/general/community-rules

## このアプリの状態

- ネット通信なし
- 広告なし
- 課金なし
- ログインなし
- 個人情報収集なし
- Android APKの無料配布予定
- 暴力、成人向け、差別、嫌がらせ表現なし
- `Othello` / `オセロ` の名称は使わず、`Reversi` / `リバーシ` を使用

## 規約面で問題になりにくい理由

- itch.ioは、ゲーム以外も含むダウンロード配布プロジェクトを公開できます。
- 無料配布なら、支払い処理や売上受け取りの設定をしなくても公開できます。
- APK配布は、Downloadable projectとして公開できます。
- このアプリは成人向け、差別的、違法、悪意あるコードを含む内容ではありません。

## まだ注意が必要な点

- キャラクター画像は、自分が配布できる権利を持っているものだけ使います。
- AI生成画像を使う場合は、使ったサービスの利用規約と商用利用条件を確認します。
- 他作品のキャラ名、画像、商標に似せすぎないようにします。
- itch.ioページでは、Google Play版ではないことを書きます。
- APKを入れるときに、端末側で「提供元不明のアプリ」の許可が必要な場合があることを書きます。

## 推奨する公開設定

- Classification: `Games`
- Kind of project: `Downloadable`
- Release status: `Released`
- Pricing: `No payments`
- Platform: `Android`
- Genre: `Puzzle`, `Strategy`
- Tags: `reversi`, `board-game`, `android`, `kotlin`, `singleplayer`, `casual`

## 公開してよいファイル

```text
publish/reversi-friend-v0.1.0.apk
screenshots/screenshot_menu.png
screenshots/screenshot_game.png
screenshots/screenshot_result.png
```

## 公開しない方がいいファイル

```text
local.properties
.gradle/
.idea/
app/build/
build/
```

これらはローカル環境用、またはビルド生成物なので、itch.ioには不要です。
