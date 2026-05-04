# Reversi Friend

`Reversi Friend` は、Android向けの1人用リバーシゲームです。  
リカとマイのキャラクター会話、CPU難易度、黒白選択を入れた、ポートフォリオ向けの小さな完成版です。

## アプリ概要

- 8x8の標準的なリバーシを遊べます。
- 最初のメニュー画面で、CPUの強さ、自分の色、自分のキャラを選べます。
- CPUは自動で1手打ちます。
- CPUの強さは `やさしい` / `ふつう` / `つよい` の3段階です。
- リカとマイのセリフが、戦況や勝敗に応じて変わります。
- 広告、課金、ログイン、ネット通信は入れていません。

## 機能一覧

- 初期配置
- 合法手判定
- 合法手ハイライト
- 石の反転
- 黒白の枚数表示
- 現在ターン表示
- パス処理
- ゲーム終了判定
- 勝敗表示
- リスタート
- メニューへ戻る
- 黒白の選択
- CPU難易度選択
- キャラクター選択
- キャラクター別の会話表示
- 勝利、敗北、引き分けの中央表示

## 使用技術

- Kotlin
- Jetpack Compose
- Material 3
- Android Gradle Plugin 8.12.1
- Gradle 8.13
- ローカル完結のゲームロジック

## ファイル構成

```text
app/src/main/kotlin/com/example/reversifriend/
  MainActivity.kt
  game/
    Board.kt
    BattleMessage.kt
    Cell.kt
    CharacterState.kt
    CpuDifficulty.kt
    CpuPlayer.kt
    GameActions.kt
    GameResult.kt
    GameState.kt
    PetCharacter.kt
    Player.kt
    Position.kt
    ReversiEngine.kt
  ui/
    ReversiFriendApp.kt
    components/
      CharacterPanel.kt
      GameResultOverlay.kt
      PlayerChoice.kt
      ReversiBoard.kt
      StatusHeader.kt
    theme/
      ReversiDesign.kt
      Theme.kt
```

## ビルド方法

Android Studioで開く場合:

1. Android Studioでこの `reversi-friend` フォルダを開きます。
2. SDKのインストールやGradle Syncを求められた場合は、Android Studioの案内に従います。
3. `Run` からエミュレーターまたは実機にインストールします。

コマンドラインでビルドする場合:

```powershell
.\gradlew.bat assembleDebug
.\gradlew.bat assembleRelease
```

APKの出力先:

```text
app/build/outputs/apk/debug/app-debug.apk
app/build/outputs/apk/release/app-release.apk
```

`app-release.apk` はローカル配布確認用です。Google Playに出す場合は、専用のリリース署名キーを作り、Android App Bundle形式で提出する想定です。

## 操作方法

1. メニュー画面でCPUの強さを選びます。
2. 自分の色を `黒` または `白` から選びます。
3. 自分のキャラを `リカ` または `マイ` から選びます。
4. `対局スタート` を押します。
5. 盤面の小さい丸が置ける場所です。
6. 自分の手を置くと、CPUが自動で打ちます。
7. 両者とも置けなくなると勝敗が表示されます。
8. `リスタート` で同じ設定のまま遊び直せます。
9. `メニューへ` で設定を選び直せます。

## 画像差し替え方法

キャラクター画像は以下に入っています。

```text
app/src/main/res/drawable-nodpi/
  cpu_rika_normal.png
  cpu_rika_advantage.png
  cpu_rika_disadvantage.png
  cpu_rika_win.png
  cpu_rika_lose.png
  cpu_rika_draw.png
  opponent_mai_normal.png
  opponent_mai_advantage.png
  opponent_mai_disadvantage.png
  opponent_mai_win.png
  opponent_mai_lose.png
  opponent_mai_draw.png
```

同じファイル名で置き換えると、コードを大きく変えずに画像を差し替えられます。  
新しいキャラを増やす場合は、`PetCharacter.kt` と `CharacterPanel.kt` の画像対応を追加します。

## セリフ差し替え方法

セリフは `BattleMessage.kt` にまとめています。  
リカ、マイ、それぞれの戦況別メッセージ配列を変更すると、会話内容を差し替えられます。

## 公開前に直した点

- `applicationId` を `com.example.reversifriend` から `com.reversifriend.app` に変更しました。
- ネット通信、広告、課金、ログインを使っていない状態にしています。
- Android Manifestのバックアップ設定を無効化しました。
- READMEを現在の機能に合わせて更新しました。
- 簡易プライバシーポリシーを `PRIVACY_POLICY.md` に追加しました。
- 公開前チェックリストを `RELEASE_CHECKLIST.md` に追加しました。

## 今後追加できる機能

- Google Play用の正式署名設定
- Android App Bundle作成
- ストア用スクリーンショット
- アプリアイコンの本制作
- キャラクター画像の権利整理
- 効果音
- 対局履歴
- 勝率表示
- 盤面テーマ変更
- 石デザイン変更

## Google Play公開前に確認すべき点

- 新規個人開発者アカウントのクローズドテスト条件は、必ずGoogle Play Consoleの最新ヘルプで確認します。
- Google公式ヘルプでは、2023年11月13日以降に作成された個人開発者アカウントは、本番公開前に12人以上のテスターが14日間継続してクローズドテストに参加していることが必要と説明されています。
- Google Playでは、2025年8月31日以降、新規アプリとアップデートはAndroid 15、API 35以上をターゲットにする必要があります。このアプリは現在 `targetSdk = 36` です。
- プライバシーポリシーが必要か確認します。
- 広告、課金、データ収集を入れる場合は、データセーフティや広告IDなどの申告が必要になる可能性があります。
- 画像素材の権利を確認します。
- AI生成画像を使う場合は、利用規約と商用利用条件を確認します。
- `オセロ` / `Othello` は使わず、アプリ名やストア説明では `リバーシ` または `Reversi` を使う方針にします。
- 対象年齢、ファミリー向けにするかどうか、ストア説明文、スクリーンショット、アプリ署名を確認します。

## 公式情報リンク

- Google Play Console: App testing requirements for new personal developer accounts  
  https://support.google.com/googleplay/android-developer/answer/14151465
- Google Play Console: Set up an open, closed, or internal test  
  https://support.google.com/googleplay/android-developer/answer/9845334
- Android Developers: Target API level requirements  
  https://developer.android.com/google/play/requirements/target-sdk
- Android Developers: Prepare and release an app  
  https://developer.android.com/studio/publish

## GitHub / itch.io公開メモ

公開手順とコピペ用テキストは `docs/` にまとめています。

```text
docs/GITHUB_PUBLISH_GUIDE.md
docs/GITHUB_RELEASE_TEXT.md
docs/ITCH_IO_PAGE_TEXT.md
docs/SCREENSHOT_GUIDE.md
docs/TESTER_RECRUITMENT_TEXT.md
```

アップロード用APKは以下に用意します。

```text
publish/reversi-friend-v0.1.0.apk
```
