package marrylab;

import com.orangesignal.csv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import marrylab.Student;
import marrylab.Table;

/**
 * CSVの入出力を司るクラスです。
 */
public abstract class IO {

	/**
	 * 表を保持するフィールドです。
	 */
	private Table table;

	/**
	 * ファイルパスを保持するフィールドです。
	 */
	private String filePass;

	private List<String[]> list;

	/**
	 * コンストラクタ：初期値を設定しておく。
	 * 
	 * @param table
	 * @param filePass
	 */
	public IO(Table table, String filePass) {
		super();

		this.table = table;
		this.filePass = filePass;
		this.list = new ArrayList<String[]>();

		return;
	}

	public void CSVtoList(){
		List<String[]> list = Csv.load(new File(this.filePass), new CsvConfig(), new StringArrayListHandler());

	}
}
