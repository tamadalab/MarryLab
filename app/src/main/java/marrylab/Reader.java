package marrylab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
* 情報を記したCSVファイルを読み込んでテーブルに仕立て上げる。
*/
public class Reader extends IO {

	/**
	 * 入力ファイルのパスを保持するフィールド
	 */
	private final String csvPath;


	/**
	 * リーダのコンストラクタ。
	 * @param aTable テーブル
	 */
	public Reader(Table aTable)
	{
		super(aTable);
		
		return;
	}

	/**
	 * CSVファイルを読み込む
	 */
	public void perform(){
		this.readCSV();
		this.setData();
	}

	public void readCSV(){

	}

	public  void setData(){

	}

}
