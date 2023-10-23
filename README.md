# config-parse

特定のルールに沿って書かれたテキストファイルを解析してCSVにするツールです

## インプット

インプットは以下のような書式です。
F5 BIG-IPのコンフィグファイルを想定しています。

ex.1
```
[セクション名] [設定名A] {
  [設定項目1] [設定内容1]
  [設定項目2] [設定内容2]
  [設定項目3] {
    [設定項目3-1] [設定内容3-1]
    [設定項目3-2] [設定内容3-2]
  }
}
[セクション名] [設定名B] {
  [設定項目1] [設定内容1]
  [設定項目2] [設定内容2]
  [設定項目3] {
    [設定項目3-1] [設定内容3-1]
    [設定項目3-2] [設定内容3-2]
    [設定項目3-3] [設定内容3-3]　←同じセクションでも設定数が違うことがあります
  }
}
```

セクション名に入る文字列のうち、解析対象はひとまず以下の通りです。

- ltm node: ノードの定義と設定。
- ltm pool: プールの設定。
- ltm virtual: バーチャルサーバの設定。
- ltm monitor: モニターの設定。
- ltm profile: プロファイルの設定。
- ltm persistence: パーシステンスプロファイルの設定。
- ltm snatpool: SNATプールの設定。
- ltm snattranslation: SNATトランスレーションの設定。
- ltm iRule: iRuleの設定。
- ltm datagroup: データグループの設定。
- net route: ルートの設定。
- net vlan: VLANの設定。
- net self: セルフIPの設定。
- sys management-ip: 管理IPの設定。
- sys global-settings: グローバル設定。

## アウトプット

解析結果を元に、各セクションごとのCSVを出力します。

前項ex.1のパース結果
```
[セクション名]
name,設定項目1,設定項目2,設定項目3,設定項目3-1,設定項目3-2,設定項目3-3
設定名A,設定内容1,設定内容2,-,設定内容3-1,設定内容3-2,-
設定名B,設定内容1,設定内容2,-,設定内容3-1,設定内容3-2,設定内容3-3
```
設定項目3のように下に階層がある場合の出力は上記のような感じでいいかなと思います。

設定名Aには存在しなかったけど設定名Bにある項目、といった場合でも、
AND条件ですべての設定項目を出力してほしいです。

以上を表にすると、このような感じになります。

[セクション名]

| name | 設定項目1 | 設定項目2 | 設定項目3 | 設定項目3-1 | 設定項目3-2 | 設定項目3-3 |
| --- | --- | --- | --- | --- | --- | --- |
| 設定名A | 設定内容1 | 設定内容2 | - | 設定内容3-1 | 設定内容3-2 | - |
| 設定名B | 設定内容1 | 設定内容2 | - | 設定内容3-1 | 設定内容3-2 | 設定内容3-3 |

