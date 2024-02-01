# MarryLab

![ML](image/ML.png)

## 概要

Javaを用いた研究室配属システム

## 説明

学生側の希望順位とそれぞれの研究側の総合点(GPA*12+コース点+教員点)の最良の組み合わせで配属先を決定するシステム

## 設定方法

+ 入力用のCSVファイルとして、以下のものを用意してください。<br>
    + コース点.csv：研究室名,ネットワークシステムの点数,情報セキュリティ,データサイエンス,ロボットインタラクション,コンピュータ基盤設計,組込みシステム,デジタルファブリケーション,脳科学,メディア処理技術,情報システム<br>
    + コース選択.csv：生徒ID,氏名,第１希望,第２希望,第３希望<br>
    + 学業成績.csv：生徒ID,氏名,特別研究１履修可否,通算GPA<br>
    + 教員点ファイルパス.csv：研究室名,教員点ファイルへのパス<br>
    + 研究室配属希望調査.csv：ID,氏名,状態,開始日時,受験完了,所要時間,評点/10.00,解答<br>
    + Lab_Score_(研究室名).csv：生徒ID,氏名,教員点(0-24)<br>

+ 入力用のJSONファイルとして、上記CSVファイルのパスとキーを指定する「files.json」を用意してください。<br>
    + キー"files"<br>
        + 学業成績(情理)_2023.csv<br>
        + コース選択.csv<br>
        + コース点.csv<br>
        + 教員点ファイルパス.csv<br>
        + 研究室配属希望調査_2023.csv<br>
    + キー"lab_score"<br>
        + Lab_Score_(研究室名).csv<br>

+ 入力用のCSVファイル,JSONファイルはapp/src/main/resouce/data内に格納してください。<br>

+ また、教員点が記されたファイル(Lab_Score_(研究室名).csv)はLab_Scoreというディレクトリを/data内に作成し、その中にまとめてください。<br>

+ Reader.javaではJSONファイルで、Writer.java内ではrunメソッド内でパス指定を行っているので、必要に応じて変更を加えてください。

## 実行方法

1. コマンドラインで「./gradlew run」を実行することで起動。<br>
2. 起動すればファイル選択ウィンドウが表示されるので、入力ファイルパスを持つJSONファイルを選択する。<br>
3. Writer.java内で指定された場所に配属結果が出力される。<br>

## 外部仕様

+ 入力 : パス指定用のJSONファイルと指定先CSVファイル<br>
+ 出力 : 結果出力用CSVファイル<br>

### 入力

1. 入力CSVファイル<br>
    + コース点.csv：研究室名,ネットワークシステムの点数,情報セキュリティ,データサイエンス,ロボットインタラクション,コンピュータ基盤設計,組込みシステム,デジタルファブリケーション,脳科学,メディア処理技術,情報システム<br>
    + コース選択.csv：生徒ID,氏名,第１希望,第２希望,第３希望<br>
    + 学業成績.csv：生徒ID,氏名,特別研究１履修可否,通算GPA<br>
    + 教員点ファイルパス.csv：研究室名,教員点ファイルへのパス<br>
    + 研究室配属希望調査.csv：ID,氏名,状態,開始日時,受験完了,所要時間,評点/10.00,解答<br>
    + Lab_Score_(研究室名).csv：生徒ID,氏名,教員点(0-24)<br>

2. 入力JSONファイル<br>
    + キー"files"<br>
        + 学業成績(情理)_2023.csv<br>
        + コース選択.csv<br>
        + コース点.csv<br>
        + 教員点ファイルパス.csv<br>
        + 研究室配属希望調査_2023.csv<br>
    + キー"lab_score"<br>
        + Lab_Score_(研究室名).csv<br>

### 出力

1. 出力CSVファイル
    + result1.csv : 学生証番号,研究室名<br>
    + result2.csv : 研究室,学生の名前(4~5人)<br>
