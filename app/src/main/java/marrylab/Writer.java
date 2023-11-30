package marrylab;

import java.util.ArrayList;
import java.util.List;

public class Writer extends IO {
    public Writer(Table table, String filePath) {
        super(table, filePath);
    }

	public void run(){
		this.write(this.table.result(), "");
	}

	/**
	 * CSVファイルを書き出すメソッドです。
	 * @param list 振り分けた結果が入っているリスト
	 * @param filePass 出力したいCSVファイルのパス
	 */
	public void write(List<String[]> list, String filepath) {
		this.ListtoCSV(list,filepass);
	}
}
