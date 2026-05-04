# GitHub公開手順

GitHubは、`Reversi Friend` を実績・ポートフォリオとして見せる場所にします。

## まず決めること

- リポジトリ名: `reversi-friend`
- 公開範囲: `Public`
- 説明文: `Android向けの1人用リバーシゲーム。Kotlin / Jetpack Composeで制作。`
- ライセンス: 最初は未設定でOK

ライセンス未設定の場合、見た人はコードを勝手に再利用しにくくなります。  
あとでオープンソースとして広く使ってほしい場合だけ、MIT Licenseなどを追加します。

## GitHubの画面でやる方法

1. GitHubにログインします。
2. 右上の `+` を押します。
3. `New repository` を押します。
4. `Repository name` に `reversi-friend` と入れます。
5. `Description` に以下を入れます。

```text
Android向けの1人用リバーシゲーム。Kotlin / Jetpack Composeで制作。
```

6. `Public` を選びます。
7. `Add a README file` はオフでOKです。
   - このプロジェクトには、すでに `README.md` があります。
8. `Create repository` を押します。

## 作ったリポジトリにアップロードするもの

基本は、このフォルダ全体をアップロードします。

```text
reversi-friend/
```

ただし、次のフォルダやファイルはアップロードしなくてOKです。

```text
.gradle/
.gradle-user-home/
.idea/
.kotlin/
.setup/
.tmp/
app/build/
build/
local.properties
publish/
```

GitHub Desktopやコマンドで公開する場合は、`.gitignore` があるので自動で除外されます。

## GitHub ReleaseにAPKを置く

ソースコードを公開したあと、遊べるAPKは `Release` に置くのがおすすめです。

1. GitHubのリポジトリを開きます。
2. 右側、または上部の `Releases` を押します。
3. `Create a new release` を押します。
4. `Choose a tag` に以下を入れます。

```text
v0.1.0
```

5. Release titleに以下を入れます。

```text
Reversi Friend v0.1.0
```

6. 説明欄には `docs/GITHUB_RELEASE_TEXT.md` の内容を貼ります。
7. APKファイルを添付します。

```text
publish/reversi-friend-v0.1.0.apk
```

8. `Publish release` を押します。

## 公開後にREADMEへ追加すると良いもの

- スマホ画面のスクリーンショット
- itch.ioページへのリンク
- GitHub ReleaseのAPKダウンロードリンク
- 制作で工夫した点
- 今後追加したい機能

## 公式ヘルプ

- GitHub: Creating a new repository  
  https://docs.github.com/repositories/creating-and-managing-repositories/creating-a-new-repository
- GitHub: Managing releases in a repository  
  https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository
