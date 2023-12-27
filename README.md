# MarryLab

## 概要

Javaを用いた研究室配属システム

## 説明

学生側の希望順位とそれぞれの研究側の総合点(GPA*12+コース点+教員点)の最良の組み合わせで配属先を決定するシステム。

## 実行方法


## 設定方法

入力用のCSVファイルとして以下のものを用意してください。<br>
コース点.csv：研究室名,ネットワークシステムの点数,情報セキュリティ,データサイエンス,ロボットインタラクション,コンピュータ基盤設計,組込みシステム,デジタルファブリケーション,脳科学,メディア処理技術,情報システム
コース選択.csv：生徒ID,氏名,第１希望,第２希望,第３希望
学業成績.csv：生徒ID,氏名,特別研究１履修可否,通算GPA
Lab_Score_(研究室名).csv：生徒ID,氏名,教員点(0-24)
教員点ファイルパス.csv：研究室名,教員点ファイルへのパス
研究室配属希望調査.csv：ID,氏名,状態,開始日時,受験完了,所要時間,評点/10.00,解答

## 外部仕様

入出力はCSVファイル<br>
# 入力
入力用のCSVファイルとして以下のものを用意してください。<br>
コース点.csv：研究室名,ネットワークシステムの点数,情報セキュリティ,データサイエンス,ロボットインタラクション,コンピュータ基盤設計,組込みシステム,デジタルファブリケーション,脳科学,メディア処理技術,情報システム<br>
コース選択.csv：生徒ID,氏名,第１希望,第２希望,第３希望<br>
学業成績.csv：生徒ID,氏名,特別研究１履修可否,通算GPA<br>
Lab_Score_(研究室名).csv：生徒ID,氏名,教員点(0-24)<br>
教員点ファイルパス.csv：研究室名,教員点ファイルへのパス<br>
研究室配属希望調査.csv：ID,氏名,状態,開始日時,受験完了,所要時間,評点/10.00,解答<br>
# 出力
1.学生証番号、研究室名<br>
２.研究室、学生の名前（4~5）<br>

## 設定方法

入力用のcsvファイルはapp/src/main/resouce/data内に格納してください。<br>
教員点が記録されたファイルはLab_Scoreディレクトリを同ディレクトリに作成し、そこにまとめてください。<br>
Reader.java、Writer.javaのrunメソッド内で、ファイルパスを指定しているので、そこを必要に応じて変更してください。<br>
