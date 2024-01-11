package marrylab;

/**
 * MarryLabの操作を行うクラスです。
 */
public class Example {
	public static void main(String[] arguments) {
		Table table = new Table();
		Reader reader = new Reader(table);
		reader.run();
		System.out.println("ファイルを読み込みました。");
		GaleShapleyMatching aGaleShapleyMatching = new GaleShapleyMatching(table);
		aGaleShapleyMatching.run();
		Writer aWriter = new Writer(table);
		aWriter.run();
		System.out.println("結果の出力が完了しました。");
	}
}