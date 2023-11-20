package marrylab;

import java.util.ArrayList;

public class Writer extends IO {
    public Writer(Table table, String filePass) {
        super(table, filePass);
    }

	/**
	 * CSVファイルを書き出すメソッドです。
	 * @param list 振り分けた結果が入っているリスト
	 * @param filePass 出力したいCSVファイルのパス
	 */
	public void write(ArrayList<String[]> list, String filepass) {
		this.ListtoCSV(list,filepass);
	}
}
