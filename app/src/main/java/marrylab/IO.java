/**
 * 入出力（リーダ、ライタ）を抽象する
 */
package marrylab;

import marrylab.Table;
public class IO {

	/**
	 * テーブルを記憶するフィールド
	 */
	private Table table;

	/**
	 * 入出力のコンストラクタ
	 * @param aTable テーブル
	 */
	public IO(Table aTable){
		this.table = aTable;

		return;
	}
	/**
	 * CSVファイルを読み込む
	 */
	public void importCsvData() {
	}

	/**
	 * CSVファイルに書き込む
	 */
	public void exportToCsv() {

	}

}
