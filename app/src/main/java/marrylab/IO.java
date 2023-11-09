package marrylab;

import marrylab.Table;

/**
 * CSVの入出力を司るクラスです。
 */
public class IO {

	/**
	 * 表を保持するフィールドです。
	 */
	private Table table;

	/**
	 * ファイルパスを保持するフィールドです。
	 */
	private String filePass;

	/**
	 * コンストラクタ：初期値を設定しておく。
	 * 
	 * @param table
	 * @param filePass
	 */
	public IO(Table table, String filePass) {
		this.table = table;
		this.filePass = filePass;
	}
}
