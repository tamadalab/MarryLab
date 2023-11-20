package marrylab;

public class Reader extends IO {
    public Reader(Table table, String filePass) {
		super(table, filePass);
    }

	/**
	 * CSVファイルを読み込むメソッドです。
	 */
	public void read() {
		this.CSVtoList();
	}
}
