package marrylab;

import java.util.ArrayList;
import java.util.List;

public class Writer extends IO {
	public Writer(Table table, String filePath) {
		super(table, filePath);
	}

	public void run() {
		this.write("./src/main/resources/result/result1.csv");
	}

	/**
	 * CSVファイルを書き出すメソッドです。
	 * 
	 * @param list     振り分けた結果が入っているリスト
	 * @param filePass 出力したいCSVファイルのパス
	 */
	public void write(String filepath) {
		List<String[]> resultList = new ArrayList<>();
		this.table.studentMap().forEach((ID, student) -> {
			// ID,生徒名,研究室名,現在の希望順位をStringの配列にしてListに入れる
			String name = student.name();
			String resultLab = student.resultLaboratory().name();
			String[] result = { ID.toString(), student.name(), student.resultLaboratory().name(),
					String.valueOf(student.currentIndex()) };
			resultList.add(result);
		});
		this.ListtoCSV(resultList, filepath);
	}
}
