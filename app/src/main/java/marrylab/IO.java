package marrylab;

import com.orangesignal.csv.*;
import com.orangesignal.csv.handlers.*;

import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.io.IOException;

import marrylab.Student;
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

	private List<String[]> list;

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

	public void CSVtoList() throws IOException{
		List<String[]> list = Csv.load(new File(this.filePass), new CsvConfig(), new StringArrayListHandler());

	}
}
