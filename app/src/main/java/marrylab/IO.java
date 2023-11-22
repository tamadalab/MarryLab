package marrylab;

import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.StringArrayListHandler;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * CSVの入出力を司るクラスです。
 */
public class IO {

	/**
	 * 表を保持するフィールドです。
	 */
	protected Table table;

	/**
	 * ファイルパスを保持するフィールドです。
	 */
	protected String filePass;


	protected List<String[]> list;

	/**
	 * コンストラクタ：初期値を設定しておく。
	 * 
	 * @param table
	 * @param filePass
	 */
	public IO(Table table, String filePass) {
		this.table = table;
		this.filePass = filePass;
		this.list = new ArrayList<String[]>();
		return;
	}

	/**
	 * CSVファイルからリストに変換するメソッドです。
	 */
	public List<String[]> CSVtoList(String filePass) {
		try {
			this.list = Csv.load(new File(filePass), 
								 new CsvConfig(), new StringArrayListHandler());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.list;
	}
	
	/**
	 * リストからCSVファイルに変換するメソッドです。
	 * @param list 振り分けた結果が入っているリスト
	 * @param filePass 出力したいCSVファイルのパス
	 */
	public void ListtoCSV(List<String[]> list, String filePass) {
		try {
			Csv.save(list, new File(filePass), new CsvConfig(), new StringArrayListHandler());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return;
	}
}

