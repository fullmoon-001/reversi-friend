# Release Checklist

`Reversi Friend` を GitHub、itch.io、Google Play などに公開する前の確認リストです。

## すぐ公開しやすい状態

- [x] アプリ名に `Othello` / `オセロ` を使っていない
- [x] ネット通信なし
- [x] ログインなし
- [x] 広告なし
- [x] 課金なし
- [x] Android Manifestに不要な権限なし
- [x] `targetSdk = 36`
- [x] `applicationId` を `com.example` 以外に変更済み
- [x] README更新済み
- [x] 簡易プライバシーポリシー追加済み

## GitHub公開前

- [x] GitHub公開手順を `docs/GITHUB_PUBLISH_GUIDE.md` に追加する
- [x] Release説明文を `docs/GITHUB_RELEASE_TEXT.md` に追加する
- [ ] スクリーンショットを追加する
- [ ] READMEにスクリーンショットを貼る
- [ ] `app-release.apk` を配布するか、itch.io側だけに置くか決める
- [ ] ソースコードを公開する場合、ライセンス方針を決める
- [ ] キャラクター画像の権利説明をREADMEに追記する

## itch.io公開前

- [x] itch.ioページ用テキストを `docs/ITCH_IO_PAGE_TEXT.md` に追加する
- [x] itch.io規約チェックを `docs/ITCH_IO_POLICY_CHECK.md` に追加する
- [x] アップロード用APKを `publish/reversi-friend-v0.1.0.apk` に用意する
- [ ] APKをアップロードする
- [ ] 対応OSをAndroidにする
- [ ] 無料、投げ銭、有料のどれにするか決める
- [ ] スクリーンショットを3枚以上用意する
- [ ] インストール方法を日本語で書く
- [ ] 「提供元不明のアプリ」許可が必要な場合があることを書く

## Google Play公開前

- [ ] Google Play用の正式なリリース署名キーを作る
- [ ] Android App Bundleを作る
- [ ] Play Consoleでアプリを作成する
- [ ] データセーフティを入力する
- [ ] プライバシーポリシーURLを用意する
- [ ] 対象年齢を設定する
- [ ] ストア説明文を作る
- [ ] スクリーンショットを作る
- [ ] アプリアイコンを本制作する
- [ ] 新規個人開発者アカウントの場合、12人以上、14日間のクローズドテスト条件を最新ヘルプで確認する

## まだ注意が必要な点

- 現在の `app-release.apk` はローカル配布確認用です。
- Google Play用には、デバッグ署名ではなく正式な署名設定が必要です。
- Google Playに一度出した `applicationId` は、基本的に後から変更できません。
- キャラクター画像を本公開する場合は、AI生成画像の利用条件と権利説明を残してください。

## 公式情報リンク

- Google Play Console: App testing requirements for new personal developer accounts  
  https://support.google.com/googleplay/android-developer/answer/14151465
- Google Play Console: Set up an open, closed, or internal test  
  https://support.google.com/googleplay/android-developer/answer/9845334
- Android Developers: Target API level requirements  
  https://developer.android.com/google/play/requirements/target-sdk
- Android Developers: Prepare and release an app  
  https://developer.android.com/studio/publish
